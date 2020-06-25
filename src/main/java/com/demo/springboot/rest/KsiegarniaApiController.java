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

    ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>(getBooks());
    ArrayList<WydawnictwoData> wydawnictwa = new ArrayList<WydawnictwoData>(getWydawnictwo());
    ArrayList<AutorData> autorzy = new ArrayList<AutorData>(getAutors());

    private static final Logger LOGGER = LoggerFactory.getLogger(KsiegarniaApiController.class);

    //http://127.0.0.1:8080/ksiegarnia/ksiazka/2
    @GetMapping(value = "/ksiazka/{id}")
    public @ResponseBody
    ResponseEntity<Ksiazka> returnKsiazka(@PathVariable Integer id) {
        refreshBooks();
        id = id - 1;
        try {
            final Ksiazka ksiazkiData = new Ksiazka(
                    ksiazki.get(id).getIdKsiazki(),
                    ksiazki.get(id).getTytul(),
                    ksiazki.get(id).getAutor(),
                    ksiazki.get(id).getWydawnictwo(),
                    ksiazki.get(id).getTemat(),
                    ksiazki.get(id).getJezykKsiazki(),
                    ksiazki.get(id).getRokWydania(),
                    ksiazki.get(id).getDostepnosc(),
                    ksiazki.get(id).getOpis());
            return new ResponseEntity<Ksiazka>(ksiazkiData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://127.0.0.1:8080/ksiegarnia/ksiazki
    @GetMapping(value = "/ksiazki")
    public @ResponseBody
    ResponseEntity<ArrayList<Ksiazka>> returnKsiazki() {
        refreshBooks();
        try {
            ArrayList<Ksiazka> ksiazkiData = ksiazki;
            return new ResponseEntity<ArrayList<Ksiazka>>(ksiazkiData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/klient/wypozyczeniaKlienta")
    public @ResponseBody
    ResponseEntity<ArrayList<WypozyczeniaKlienta>> returnWypozyczeniaKlienta(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {
        // refreshBooks();
        try {

            ArrayList<WypozyczeniaKlienta> wypozyczeniaKlienta = new ArrayList<WypozyczeniaKlienta>(getWypozyczeniaKlienta(login, password));
            return new ResponseEntity<ArrayList<WypozyczeniaKlienta>>(wypozyczeniaKlienta, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @Scheduled(fixedRate = 5000)/////////////////////////////////////////TODO::: TERAZ ODSWIEZAJA SIE TYLKO PO  GET
    public void refreshBooks() {
        LOGGER.info("Odswiezam ksiazki bo dostalem GET");
        ksiazki = new ArrayList<Ksiazka>(getBooks());
    }

    public void refreshWydawnictwa() {
        LOGGER.info("Odswiezam Wydawnictwa bo dostalem GET");
        wydawnictwa = new ArrayList<WydawnictwoData>(getWydawnictwo());
    }

    public void refreshAutors() {
        LOGGER.info("Odswiezam Autorów bo dostalem GET");
        autorzy = new ArrayList<AutorData>(getAutors());
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
    public ResponseEntity<LoginData> test5(@RequestBody LoginData loginValues) {
        if (isLoginAndPasswordRightClient(loginValues.getLogin(), loginValues.getPassword())) {
            LOGGER.info(loginValues.toString());
            LOGGER.info("Login i haslo sie zgadzaja.");
            return new ResponseEntity<LoginData>(loginValues, HttpStatus.OK);
        } else {
            LOGGER.info("Login i haslo sie nie zgadza.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //TODO:: to jest prymitywnie, pasuje zmienic
        }
    }

    //http://127.0.0.1:8080/ksiegarnia/loginAdmin
    @PostMapping(value = "/loginAdmin")
    public ResponseEntity<LoginData> test6(@RequestBody LoginData loginValues) {
        if (isLoginAndPasswordRightAdmin(loginValues.getLogin(), loginValues.getPassword())) {
            LOGGER.info(loginValues.toString());
            LOGGER.info("Login i haslo sie zgadzaja.");
            return new ResponseEntity<LoginData>(loginValues, HttpStatus.OK);
        } else {
            LOGGER.info("Login i haslo sie nie zgadza.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity test6(@RequestBody KlientData registerValues) {
        if (registerData(registerValues.getImie(), registerValues.getNazwisko(), registerValues.getLogin(), registerValues.getHaslo(), registerValues.getKodPocztowy(), registerValues.getTelefon(), registerValues.getMiejscowosc(), registerValues.getUlica(), registerValues.getNrDomu()) != 0) {
            LOGGER.info(registerValues.toString());
            LOGGER.info("zarejestrowano.");
            return new ResponseEntity<KlientData>(registerValues, HttpStatus.OK);
        } else
            LOGGER.info("istnieje taki login ERROR.");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/update")
    public ResponseEntity test7(@RequestBody KlientData updatedValues) {
        if (updateData(updatedValues.getImie(), updatedValues.getNazwisko(), updatedValues.getLogin(), updatedValues.getHaslo(), updatedValues.getKodPocztowy(), updatedValues.getTelefon(), updatedValues.getMiejscowosc(), updatedValues.getUlica(), updatedValues.getNrDomu()) != 0) {
            LOGGER.info(updatedValues.toString());
            LOGGER.info("Zaktualizowano.");
            return new ResponseEntity<KlientData>(updatedValues, HttpStatus.OK);
        } else
            LOGGER.info("Brak zmian");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //http://127.0.0.1:8080/ksiegarnia/klient?login=kamilabudzik&password=gabigabi
    @GetMapping(value = "/klient")
    public @ResponseBody
    ResponseEntity<ArrayList<KlientData>> returnKlient(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {

        if (login != null && password != null && getKlient(login, password) != null) {
            ArrayList<KlientData> daneKlienta = new ArrayList<KlientData>(getKlient(login, password));
            LOGGER.info(login);
            LOGGER.info(password);
            LOGGER.info(Arrays.toString(daneKlienta.toArray()));

            return new ResponseEntity<ArrayList<KlientData>>(daneKlienta, HttpStatus.OK);
        } else
            LOGGER.info("null");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    //http://127.0.0.1:8080/ksiegarnia/klient/usunkonto?login=zxcasdz&password=zxcasd
    @DeleteMapping(value = "/klient/usunkonto")
    public @ResponseBody
    ResponseEntity<LoginData> deleteklient(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {

        if (login != null && password != null && deleteClient(login, password) == 1) {
            LoginData loginData = new LoginData(login, password);
            deleteClient(login, password);
            LOGGER.info("Pomyślnie skasowano konto");

            return new ResponseEntity<LoginData>(loginData, HttpStatus.OK);
        } else
            LOGGER.info("Blad przy kasowaniu konta");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //http://127.0.0.1:8080/ksiegarnia/ksiazki/wypozycz?login=zxcasd&password=zxcasdd&idKsiazki=1
    @PostMapping(value = "/ksiazki/wypozycz")
    public @ResponseBody
    ResponseEntity<LoginData> wypozyczKsiazkeAPI(@RequestParam(value = "login", required = true) String login,
                                                 @RequestParam(value = "password", required = true) String password,
                                                 @RequestParam(value = "idKsiazki", required = true) Integer idKsiazki) {
        if (login != null && password != null && idKsiazki != null
                && !jestJuzWypozyczona(login, password, idKsiazki)
                && isLoginAndPasswordRightClient(login, password)) {

            LOGGER.info("LOGIN:" + login);
            LOGGER.info("haslo:" + password);
            LOGGER.info("idksiazki:" + idKsiazki);
            wypozyczKsiazke(login, password, idKsiazki);
            LoginData loginData = new LoginData(login, password);
            return new ResponseEntity<LoginData>(loginData, HttpStatus.OK);
        } else
            LOGGER.info("Blad przy wypozyczeniu");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addWydawnictwo")
    public ResponseEntity test10(@RequestBody WydawnictwoData wydawnictwoValues) {
        if (addWydawnictwo(wydawnictwoValues.getNazwa(), wydawnictwoValues.getMiasto()) != 0) {
            LOGGER.info(wydawnictwoValues.toString());
            LOGGER.info("dodano wydawnictwo.");
            return new ResponseEntity<WydawnictwoData>(wydawnictwoValues, HttpStatus.OK);
        } else
            LOGGER.info("istnieje takie wydawnictwo ERROR.");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addAutor")
    public ResponseEntity test11(@RequestBody AutorData autorValues) {
        if (addAutor(autorValues.getNazwisko(), autorValues.getImie(), autorValues.getNarodowosc(), autorValues.getOkres_tworzenia(), autorValues.getJezyk()) != 0) {
            LOGGER.info(autorValues.toString());
            LOGGER.info("dodano autora.");
            return new ResponseEntity<AutorData>(autorValues, HttpStatus.OK);
        } else
            LOGGER.info("istnieje taki autor ERROR.");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

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

    @GetMapping(value = "/getAutorzy")
    public @ResponseBody
    ResponseEntity<ArrayList<AutorData>> returnAutors() {
        try {
            ArrayList<AutorData> autorData = autorzy;
            return new ResponseEntity<ArrayList<AutorData>>(autorData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/addBook")
    public ResponseEntity test12(@RequestBody Ksiazka ksiazkaValues) {
        if (ksiazkaValues != null && ksiazkaValues.getTytul() != null &&ksiazkaValues.getTytul()!=null&& ksiazkaValues.getImieAutora() != null
                && ksiazkaValues.getNazwiskoAutora() != null &&ksiazkaValues.getWydawnictwo()!=null && ksiazkaValues.getJezykKsiazki()!=null
                &&ksiazkaValues.getRokWydania()!=null && ksiazkaValues.getDostepnosc()!=null&&ksiazkaValues.getOpis()!=null) {

            LOGGER.info("tytul: " + ksiazkaValues.getTytul());
            LOGGER.info("temat: " + ksiazkaValues.getTemat());
            LOGGER.info("imieAutora: " + ksiazkaValues.getImieAutora());
            LOGGER.info("nazwiskloAutora: " + ksiazkaValues.getNazwiskoAutora());
            LOGGER.info("wydawn: " + ksiazkaValues.getWydawnictwo());
            LOGGER.info("jezyk:+ " + ksiazkaValues.getJezykKsiazki());
            LOGGER.info("rok: " + ksiazkaValues.getRokWydania());
            LOGGER.info("dost: " + ksiazkaValues.getDostepnosc());
            LOGGER.info("opis: " + ksiazkaValues.getOpis());




          /*  if (addBook(ksiazkaValues.getTytul(), ksiazkaValues.getTemat(), ksiazkaValues.getJezykKsiazki(), ksiazkaValues.getRokWydania(), ksiazkaValues.getDostepnosc(), ksiazkaValues.getOpis(), ksiazkaValues.getAutor()) != 0) {
                LOGGER.info(ksiazkaValues.toString());
                LOGGER.info("dodano ksiazke.");
                return new ResponseEntity<Ksiazka>(ksiazkaValues, HttpStatus.OK);
            }*/
        } else
            LOGGER.info("null.");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
