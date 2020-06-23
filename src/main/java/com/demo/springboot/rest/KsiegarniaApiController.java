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

    private static final Logger LOGGER = LoggerFactory.getLogger(KsiegarniaApiController.class);

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

    // @Scheduled(fixedRate = 5000)/////////////////////////////////////////TODO::: TERAZ ODSWIEZAJA SIE TYLKO PO  GET
    public void refreshBooks() {
        LOGGER.info("Odswiezam ksiazki bo dostalem GET");
        ksiazki = new ArrayList<Ksiazka>(getBooks());
    }

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

    @PostMapping(value = "/login")
    public ResponseEntity<LoginData> test5(@RequestBody LoginData loginValues) {
        if (isLoginAndPasswordRightClient(loginValues.getLogin().toString(), loginValues.getPassword().toString()) == true) {
            LOGGER.info(loginValues.toString());
            LOGGER.info("Login i haslo sie zgadzaja.");
            return new ResponseEntity<LoginData>(loginValues, HttpStatus.OK);
        } else {
            LOGGER.info("Login i haslo sie nie zgadza.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/loginAdmin")
    public ResponseEntity<LoginData> test6(@RequestBody LoginData loginValues) {
        if (isLoginAndPasswordRightAdmin(loginValues.getLogin().toString(), loginValues.getPassword().toString()) == true) {
            LOGGER.info(loginValues.toString());
            LOGGER.info("Login i haslo sie zgadzaja.");
            return new ResponseEntity<LoginData>(loginValues, HttpStatus.OK);
        } else {
            LOGGER.info("Login i haslo sie nie zgadza.");
            //  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return null;// TODO:: PRYMITYWNE _ DO ZMIANY
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity test6(@RequestBody KlientData registerValues) {
        if (registerData(registerValues.getImie().toString(), registerValues.getNazwisko().toString(), registerValues.getLogin().toString(), registerValues.getHaslo().toString(), registerValues.getKodPocztowy().toString(), registerValues.getTelefon().toString(), registerValues.getMiejscowosc().toString(), registerValues.getUlica().toString(), registerValues.getNrDomu().toString()) != 0) {
            LOGGER.info(registerValues.toString());
            LOGGER.info("zarejestrowano.");
            return new ResponseEntity<KlientData>(registerValues, HttpStatus.OK);
        } else
            LOGGER.info("istnieje taki login ERROR.");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/update")
    public ResponseEntity test7(@RequestBody KlientData updatedValues) {
        if (updateData(updatedValues.getImie().toString(), updatedValues.getNazwisko().toString(), updatedValues.getLogin().toString(), updatedValues.getHaslo().toString(), updatedValues.getKodPocztowy().toString(), updatedValues.getTelefon().toString(), updatedValues.getMiejscowosc().toString(), updatedValues.getUlica().toString(), updatedValues.getNrDomu().toString()) != 0) {
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
            LOGGER.info("KONIEC ARRAYA MOJEGO");

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
            LOGGER.info(login);
            LOGGER.info(password);
            LoginData loginData = new LoginData(login, password);
            deleteClient(login, password);

            return new ResponseEntity<LoginData>(loginData, HttpStatus.OK);
        } else
            LOGGER.info("null");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
