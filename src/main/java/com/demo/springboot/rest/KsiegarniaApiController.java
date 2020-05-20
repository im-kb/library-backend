package com.demo.springboot.rest;

import com.demo.springboot.service.impl.DBCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.springboot.domain.dto.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/ksiegarnia")
public class KsiegarniaApiController {

    ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>(DBCode.readKsiazki());
    private static final Logger LOGGER = LoggerFactory.getLogger(KsiegarniaApiController.class);

    @GetMapping("/ksiazki")
    public @ResponseBody
    ResponseEntity<ReturnKsiazka> ReturnKsiazka() {
        try {
            ReturnKsiazka ksiazkiData = new ReturnKsiazka(
                   // while(ksiazki.get(0)!= null)
                    ksiazki.get(1).getIdKsiazki(),
                    ksiazki.get(1).getTytul(),
                    ksiazki.get(1).getIdAutora(),
                    ksiazki.get(1).getIdWydawnictwa(),
                    ksiazki.get(1).getTemat(),
                    ksiazki.get(1).getJezykKsiazki(),
                    ksiazki.get(1).getRokWydania(),
                    ksiazki.get(1).getDostepnosc());
            return new ResponseEntity<ReturnKsiazka>(ksiazkiData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}