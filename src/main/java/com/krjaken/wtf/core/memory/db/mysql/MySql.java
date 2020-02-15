package com.krjaken.wtf.core.memory.db.mysql;


import com.krjaken.wtf.core.memory.db.dtos.LanguageDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySql implements JpaRepository<LanguageDto, Long> {

    @Override
    public List<LanguageDto> findAll() {
        return null;
    }

    @Override
    public List<LanguageDto> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<LanguageDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<LanguageDto> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(LanguageDto languageDto) {

    }

    @Override
    public void deleteAll(Iterable<? extends LanguageDto> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends LanguageDto> S save(S s) {
        return null;
    }

    @Override
    public <S extends LanguageDto> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<LanguageDto> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends LanguageDto> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<LanguageDto> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public LanguageDto getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends LanguageDto> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends LanguageDto> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends LanguageDto> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends LanguageDto> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends LanguageDto> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends LanguageDto> boolean exists(Example<S> example) {
        return false;
    }
}
