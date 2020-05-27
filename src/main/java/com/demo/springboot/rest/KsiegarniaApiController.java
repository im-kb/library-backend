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

import static com.demo.springboot.service.impl.DBManager.readKsiazki;

@RestController
@RequestMapping("/ksiegarnia")

public class KsiegarniaApiController {

    ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>(readKsiazki());
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

    // @Scheduled(fixedRate = 5000)/////////////////////////////////////////TODO:::
    public void refreshBooks() {
        LOGGER.info("Odswiezam ksiazki bo dostalem GET");
        ksiazki = new ArrayList<Ksiazka>(readKsiazki());
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
}