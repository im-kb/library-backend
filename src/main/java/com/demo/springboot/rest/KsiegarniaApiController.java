package com.demo.springboot.rest;

import com.demo.springboot.service.impl.DBCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.springboot.domain.dto.*;

import java.util.ArrayList;
@RestController
public class KsiegarniaApiController {
    ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>(DBCode.readKsiazki());
    private static final Logger LOGGER = LoggerFactory.getLogger(KsiegarniaApiController.class);

    @Autowired
    @RequestMapping(value = "/ksiegarnia/getksiazka/{id2}", method = RequestMethod.GET)
    public ResponseEntity<ReturnKsiazka> test(@PathVariable("id2") Integer id2) {
        try {
            final ReturnKsiazka ksiazkiData = new ReturnKsiazka(
                    ksiazki.get(id2).getIdKsiazki(),
                    ksiazki.get(id2).getTytul(),
                    ksiazki.get(id2).getIdAutora(),
                    ksiazki.get(id2).getWydawnictwo(),
                    ksiazki.get(id2).getTemat(),
                    ksiazki.get(id2).getJezykKsiazki(),
                    ksiazki.get(id2).getRokWydania(),
                    ksiazki.get(id2).getDostepnosc());
            return new ResponseEntity<ReturnKsiazka>(ksiazkiData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}