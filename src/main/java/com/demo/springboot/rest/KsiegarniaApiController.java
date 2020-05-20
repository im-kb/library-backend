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

    @GetMapping(value="/ksiazka/{id}")
    public @ResponseBody ResponseEntity<Ksiazka> returnKsiazka(@PathVariable Integer id) {
        id=id-1;
        try {
            final Ksiazka ksiazkiData = new Ksiazka(
                    ksiazki.get(id).getIdKsiazki(),
                    ksiazki.get(id).getTytul(),
                    ksiazki.get(id).getIdAutora(),
                    ksiazki.get(id).getIdWydawnictwa(),
                    ksiazki.get(id).getTemat(),
                    ksiazki.get(id).getJezykKsiazki(),
                    ksiazki.get(id).getRokWydania(),
                    ksiazki.get(id).getDostepnosc());
            return new ResponseEntity<Ksiazka>(ksiazkiData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value="/ksiazki")
    public @ResponseBody ResponseEntity<ArrayList<Ksiazka>>returnKsiazki(){
        try {
            ArrayList<Ksiazka>ksiazkiData=ksiazki;
            return new ResponseEntity<ArrayList<Ksiazka>>(ksiazkiData, HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }
