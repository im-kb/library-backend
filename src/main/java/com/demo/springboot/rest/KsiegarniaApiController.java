package com.demo.springboot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.commons.io.IOUtils;

import org.springframework.web.bind.annotation.*;
import com.demo.springboot.domain.dto.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static com.demo.springboot.service.impl.DBManager.*;


@RestController
@RequestMapping("/ksiegarnia")

public class KsiegarniaApiController {

    ArrayList<BookDto> ksiazki = new ArrayList<BookDto>(getBooks());
    ArrayList<WydawnictwoData> wydawnictwa = new ArrayList<WydawnictwoData>(getWydawnictwo());
    ArrayList<AuthorDto> autorzy = new ArrayList<AuthorDto>(getAutors());

    private static final Logger LOGGER = LoggerFactory.getLogger(KsiegarniaApiController.class);

    //http://127.0.0.1:8080/ksiegarnia/ksiazka/2
    @GetMapping(value = "/ksiazka/{id}")
    public @ResponseBody
    ResponseEntity<BookDto> returnKsiazka(@PathVariable Integer id) {
        refreshBooks();
        id = id - 1;
        try {
            final BookDto ksiazkiData = new BookDto(
                    ksiazki.get(id).getBookId(),
                    ksiazki.get(id).getTitle(),
                    ksiazki.get(id).getAuthor(),
                    ksiazki.get(id).getPublishingHouse(),
                    ksiazki.get(id).getTopic(),
                    ksiazki.get(id).getBookLanguage(),
                    ksiazki.get(id).getPublicationDate(),
                    ksiazki.get(id).getAvailability(),
                    ksiazki.get(id).getDescription());
            return new ResponseEntity<BookDto>(ksiazkiData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://127.0.0.1:8080/ksiegarnia/ksiazki
    @GetMapping(value = "/ksiazki")
    public @ResponseBody
    ResponseEntity<ArrayList<BookDto>> returnKsiazki() {
        refreshBooks();
        try {
            ArrayList<BookDto> ksiazkiData = ksiazki;
            return new ResponseEntity<ArrayList<BookDto>>(ksiazkiData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://127.0.0.1:8080/ksiegarnia/klient/wypozyczeniaKlienta?login=zxcasd&password=zxcasd
    @GetMapping(value = "/klient/wypozyczeniaKlienta")
    public @ResponseBody
    ResponseEntity<ArrayList<WypozyczeniaKlienta>> returnWypozyczeniaKlienta(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {
        try {
            ArrayList<WypozyczeniaKlienta> wypozyczeniaKlienta = new ArrayList<WypozyczeniaKlienta>(getWypozyczeniaKlienta(login, password));
            return new ResponseEntity<ArrayList<WypozyczeniaKlienta>>(wypozyczeniaKlienta, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://127.0.0.1:8080/ksiegarnia/klient/wypozyczeniaKlientow?login=admin&password=admin
    @GetMapping(value = "/klient/wypozyczeniaKlientow")
    public @ResponseBody
    ResponseEntity<ArrayList<WypozyczeniaKlientow>> returnWypozyczeniaKlientow(@RequestParam(value = "login", required = true) String login,
                                                                               @RequestParam(value = "password", required = true) String password) {
        try {
            if (login != null && password != null && isLoginAndPasswordRightAdmin(login, password)) {

                ArrayList<WypozyczeniaKlientow> wypozyczeniaKlientow = new ArrayList<WypozyczeniaKlientow>(getWypozyczeniaKlientow(login, password));
                return new ResponseEntity<ArrayList<WypozyczeniaKlientow>>(wypozyczeniaKlientow, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @Scheduled(fixedRate = 5000)/////////////////////////////////////////TODO::: TERAZ ODSWIEZAJA SIE TYLKO PO  GET
    public void refreshBooks() {
        LOGGER.info("Odswiezam ksiazki bo dostalem GET");
        ksiazki = new ArrayList<BookDto>(getBooks());
    }

    //http://127.0.0.1:8080/ksiegarnia/image/2
    @GetMapping(
            value = "/image/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE

    )
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable Integer id) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/imgs/" + id + ".jpg");
        return IOUtils.toByteArray(in);
    }

    /*http://127.0.0.1:8080/ksiegarnia/login
    {
        "login": "kamilabudzik",
            "password": "gabigabi"
    }*/
    @PostMapping(value = "/login")
    public ResponseEntity<LoginDto> test5(@RequestBody LoginDto loginValues) {
        if (isLoginAndPasswordRightClient(loginValues.getLogin(), loginValues.getPassword())) {
            LOGGER.info(loginValues.toString());
            LOGGER.info("Login i haslo sie zgadzaja.");
            return new ResponseEntity<>(loginValues, HttpStatus.OK);
        } else {
            LOGGER.info("Login i haslo sie nie zgadza.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //TODO:: to jest prymitywnie, pasuje zmienic
        }
    }

    //http://127.0.0.1:8080/ksiegarnia/loginAdmin
    @PostMapping(value = "/loginAdmin")
    public ResponseEntity<LoginDto> test6(@RequestBody LoginDto loginValues) {
        if (isLoginAndPasswordRightAdmin(loginValues.getLogin(), loginValues.getPassword())) {
            LOGGER.info(loginValues.toString());
            LOGGER.info("Login i haslo sie zgadzaja.");
            return new ResponseEntity<LoginDto>(loginValues, HttpStatus.OK);
        } else {
            LOGGER.info("Login i haslo sie nie zgadza.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity test6(@RequestBody ClientDto registerValues) {
        if (registerData(registerValues.getName(), registerValues.getSurname(), registerValues.getLogin(), registerValues.getPassword(), registerValues.getPostalCode(), registerValues.getPhoneNumber(), registerValues.getCity(), registerValues.getStreet(), registerValues.getHouseNumber()) != 0) {
            LOGGER.info(registerValues.toString());
            LOGGER.info("zarejestrowano.");
            return new ResponseEntity<ClientDto>(registerValues, HttpStatus.OK);
        } else
            LOGGER.info("istnieje taki login ERROR.");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/update")
    public ResponseEntity test7(@RequestBody ClientDto updatedValues) {
        if (updateData(updatedValues.getName(), updatedValues.getSurname(), updatedValues.getLogin(), updatedValues.getPassword(), updatedValues.getPostalCode(), updatedValues.getPhoneNumber(), updatedValues.getCity(), updatedValues.getStreet(), updatedValues.getHouseNumber()) != 0) {
            LOGGER.info(updatedValues.toString());
            LOGGER.info("Zaktualizowano.");
            return new ResponseEntity<ClientDto>(updatedValues, HttpStatus.OK);
        } else
            LOGGER.info("Brak zmian");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //http://127.0.0.1:8080/ksiegarnia/klient?login=kamilabudzik&password=gabigabi
    @GetMapping(value = "/klient")
    public @ResponseBody
    ResponseEntity<ArrayList<ClientDto>> returnKlient(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {

        if (login != null && password != null && getKlient(login, password) != null) {
            ArrayList<ClientDto> daneKlienta = new ArrayList<ClientDto>(getKlient(login, password));
            LOGGER.info(login);
            LOGGER.info(password);
            LOGGER.info(Arrays.toString(daneKlienta.toArray()));

            return new ResponseEntity<ArrayList<ClientDto>>(daneKlienta, HttpStatus.OK);
        } else
            LOGGER.info("null");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    //http://127.0.0.1:8080/ksiegarnia/klient/usunkonto?login=zxcasdz&password=zxcasd
    @DeleteMapping(value = "/klient/usunkonto")
    public @ResponseBody
    ResponseEntity<LoginDto> deleteklient(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {

        if (login != null && password != null && deleteClient(login, password) == 1) {
            LoginDto loginDto = new LoginDto(login, password);
            deleteClient(login, password);
            LOGGER.info("Pomyślnie skasowano konto");

            return new ResponseEntity<LoginDto>(loginDto, HttpStatus.OK);
        } else
            LOGGER.info("Blad przy kasowaniu konta");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //http://127.0.0.1:8080/ksiegarnia/ksiazki/wypozycz?login=zxcasd&password=zxcasdd&idKsiazki=1
    @PostMapping(value = "/ksiazki/wypozycz")
    public @ResponseBody
    ResponseEntity<LoginDto> wypozyczKsiazkeAPI(@RequestParam(value = "login", required = true) String login,
                                                @RequestParam(value = "password", required = true) String password,
                                                @RequestParam(value = "idKsiazki", required = true) Integer idKsiazki) {
        if (login != null && password != null && idKsiazki != null
                && !jestJuzWypozyczona(idKsiazki)
                && isLoginAndPasswordRightClient(login, password)
                && czyIstniejeKsiazka(idKsiazki) == true) {

            LOGGER.info("LOGIN:" + login);
            LOGGER.info("haslo:" + password);
            LOGGER.info("idksiazki:" + idKsiazki);
            wypozyczKsiazke(login, password, idKsiazki);
            LoginDto loginDto = new LoginDto(login, password);
            return new ResponseEntity<LoginDto>(loginDto, HttpStatus.OK);
        } else
            LOGGER.info("Czy jest wypozyczona:" + jestJuzWypozyczona(idKsiazki));
        LOGGER.info("Blad przy wypozyczeniu");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //http://127.0.0.1:8080/ksiegarnia/ksiazki/usunWypozyczenie?login=admin&password=admin&idKsiazki=4&idKlienta=36
    @DeleteMapping(value = "/ksiazki/usunWypozyczenie")
    public @ResponseBody
    ResponseEntity<LoginDto> usunWypozyczenieAPI(@RequestParam(value = "login", required = true) String login,
                                                 @RequestParam(value = "password", required = true) String password,
                                                 @RequestParam(value = "idKsiazki", required = true) Integer idKsiazki,
                                                 @RequestParam(value = "idKlienta", required = true) Integer idKlienta) {

        if (login != null && password != null && idKsiazki != null
                && jestJuzWypozyczona(idKsiazki)
                && isLoginAndPasswordRightAdmin(login, password)) {

            LOGGER.info("LOGIN:" + login);
            LOGGER.info("haslo:" + password);
            LOGGER.info("idksiazki:" + idKsiazki);
            usunWypozyczenie(idKsiazki, idKlienta);
            LoginDto loginDto = new LoginDto(login, password);
            return new ResponseEntity<LoginDto>(loginDto, HttpStatus.OK);
        } else
            LOGGER.info("Czy jest wypozyczona:" + jestJuzWypozyczona(idKsiazki));
        LOGGER.info("Blad przy usuwaniu wypozyzcania");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //http://127.0.0.1:8080/ksiegarnia/ksiazki/usunKsiazke?login=admin&password=admin&idKsiazki=10
    @DeleteMapping(value = "/ksiazki/usunKsiazke")
    public @ResponseBody
    ResponseEntity<LoginDto> usunKsiazkeAPI(@RequestParam(value = "login", required = true) String login,
                                            @RequestParam(value = "password", required = true) String password,
                                            @RequestParam(value = "idKsiazki", required = true) Integer idKsiazki) {
        if (login != null && password != null && idKsiazki != null && isLoginAndPasswordRightAdmin(login, password)
                && jestJuzWypozyczona(idKsiazki) != true && czyIstniejeKsiazka(idKsiazki) == true) {

            LOGGER.info("LOGIN wypozycz:" + login);
            LOGGER.info("haslo wypozycz:" + password);
            LOGGER.info("idksiazki wypozycz:" + idKsiazki);
            LOGGER.info("Czy jest wypozyczona:" + jestJuzWypozyczona(idKsiazki));

            usunKsiazke(idKsiazki);
            LoginDto loginDto = new LoginDto(login, password);
            return new ResponseEntity<LoginDto>(loginDto, HttpStatus.OK);
        } else
            LOGGER.info("Blad przy usuwaniu");
        LOGGER.info("Czy jest wypozyczona:" + jestJuzWypozyczona(idKsiazki));
        LOGGER.info("Czy istnieje:" + czyIstniejeKsiazka(idKsiazki));
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addWydawnictwo")
    public ResponseEntity test10(@RequestBody WydawnictwoData wydawnictwoValues) {
        if (addWydawnictwo(wydawnictwoValues.getNazwa(), wydawnictwoValues.getMiasto()) != 0) {
            LOGGER.info(wydawnictwoValues.toString());
            LOGGER.info("dodano wydawnictwo.");
            refreshWydawnictwa();
            LOGGER.info("odswiezono wydawnictwa.");

            return new ResponseEntity<WydawnictwoData>(wydawnictwoValues, HttpStatus.OK);
        } else
            LOGGER.info("istnieje takie wydawnictwo ERROR.");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addAutor")
    public ResponseEntity test11(@RequestBody AuthorDto autorValues) {
        if (addAutor(autorValues.getSurname(), autorValues.getName(), autorValues.getNationality(), autorValues.getCreationPeriod(), autorValues.getLanguage()) != 0) {
            LOGGER.info(autorValues.toString());
            LOGGER.info("dodano autora.");
            refreshAutors();
            LOGGER.info("odswiezono autorow.");

            return new ResponseEntity<AuthorDto>(autorValues, HttpStatus.OK);
        } else
            LOGGER.info("istnieje taki autor ERROR.");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public void refreshWydawnictwa() {
        LOGGER.info("Odswiezam Wydawnictwa bo dostalem GET");
        wydawnictwa = new ArrayList<WydawnictwoData>(getWydawnictwo());
    }

    public void refreshAutors() {
        LOGGER.info("Odswiezam Autorów bo dostalem GET");
        autorzy = new ArrayList<AuthorDto>(getAutors());
    }

    //http://127.0.0.1:8080/ksiegarnia/getWydawnictwa
    @GetMapping(value = "/getWydawnictwa")
    public @ResponseBody
    ResponseEntity<ArrayList<WydawnictwoData>> returnWydawnictwa() {
        try {
            ArrayList<WydawnictwoData> wydawnictwoData = wydawnictwa;

            return new ResponseEntity<ArrayList<WydawnictwoData>>(wydawnictwoData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://127.0.0.1:8080/ksiegarnia/getAutorzy
    @GetMapping(value = "/getAutorzy")
    public @ResponseBody
    ResponseEntity<ArrayList<AuthorDto>> returnAutors() {
        try {
            ArrayList<AuthorDto> authorData = autorzy;
            return new ResponseEntity<ArrayList<AuthorDto>>(authorData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/addBook")
    public ResponseEntity test12(@RequestBody BookDto bookDtoValues) {
        if (bookDtoValues != null && bookDtoValues.getTitle() != null && bookDtoValues.getTitle() != null && bookDtoValues.getAuthorName() != null
                && bookDtoValues.getAuthorSurname() != null && bookDtoValues.getPublishingHouse() != null && bookDtoValues.getBookLanguage() != null
                && bookDtoValues.getPublicationDate() != null && bookDtoValues.getAvailability() != null && bookDtoValues.getDescription() != null) {

            LOGGER.info("tytul: " + bookDtoValues.getTitle());
            LOGGER.info("temat: " + bookDtoValues.getTopic());
            LOGGER.info("imieAutora: " + bookDtoValues.getAuthorName());
            LOGGER.info("nazwiskloAutora: " + bookDtoValues.getAuthorSurname());
            LOGGER.info("wydawn: " + bookDtoValues.getPublishingHouse());
            LOGGER.info("jezyk: " + bookDtoValues.getBookLanguage());
            LOGGER.info("rok: " + bookDtoValues.getPublicationDate());
            LOGGER.info("dost: " + bookDtoValues.getAvailability());
            LOGGER.info("opis: " + bookDtoValues.getDescription());
            if (addBook(bookDtoValues.getTitle(), bookDtoValues.getTopic(), bookDtoValues.getBookLanguage(), bookDtoValues.getPublicationDate(), bookDtoValues.getAvailability(), bookDtoValues.getDescription(), bookDtoValues.getAuthorName(), bookDtoValues.getAuthorSurname(), bookDtoValues.getPublishingHouse()) != 0) {
                LOGGER.info(bookDtoValues.toString());
                LOGGER.info("dodano ksiazke.");
                return new ResponseEntity<BookDto>(bookDtoValues, HttpStatus.OK);

            } else {
                LOGGER.info("null.");
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
