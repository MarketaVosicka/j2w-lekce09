package cz.czechitas.java2webapps.lekce9.service;

import cz.czechitas.java2webapps.lekce9.entity.Adresa;
import cz.czechitas.java2webapps.lekce9.entity.Osoba;
import cz.czechitas.java2webapps.lekce9.repository.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Služba pro práci s osobami a adresami.
 */
@Service
public class OsobaService {
    private final OsobaRepository osobaRepository;

    @Autowired
    public OsobaService(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }

    /**
     * Vrací stránkovaný seznam všech osob v databázi seřazených podle příjmení a jména.
     */
    public Page<Osoba> seznamOsob(Pageable pageable) {
        return osobaRepository.findAll(pageable);
    }

    /**
     * Vrací seznam všech osob v db narozených v určitém rozpětí
     *
     * @param rokOd
     * @param rokDo
     * @param pageable
     * @return
     */
    public Page<Osoba> seznamDleDataNarozeni(int rokOd, int rokDo, Pageable pageable) {
        return osobaRepository.findByRok(rokOd, rokDo, pageable);
    }

    /**
     * Vrací seznam všech osob v db jejichž přijmení začíná zadaným parametrem
     *
     * @param prijmeni
     * @param pageable
     * @return
     */
    public Page<Osoba> seznamDleZacatkuPrijmeni(String prijmeni, Pageable pageable) {
        return osobaRepository.findByPrijmeniStartingWithIgnoreCase(prijmeni, pageable);
    }

    /**
     * Vrací seznam všech osob v db dle zadané obce
     *
     * @param obec
     * @param pageable
     * @return
     */
    public Page<Osoba> seznamDleObce(String obec, Pageable pageable) {
        return osobaRepository.findByObec(obec, pageable);
    }

    /**
     * Vrací seznam všech osob v db dle zadaného minálního věku
     * @param vek
     * @param pageable
     * @return
     */
    public Page<Osoba> seznamDleVeku(int vek, Pageable pageable) {
        LocalDate datum = LocalDate.now().minusYears(vek);
        return osobaRepository.findByDatumNarozeniBefore(datum, pageable);
    }
}
