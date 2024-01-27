package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.citystreet.CityStreetCreateRequestDto;
import com.trans.api.dto.citystreet.CityStreetResponseDto;
import com.trans.api.dto.citystreet.CityStreetUpdateRequestDto;
import com.trans.api.service.CityStreetService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "cities/{cityId}/streets")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CityStreetController {
    CityStreetService service;

    public static final String GET_BY_ID = "{streetId}";

    @GetMapping
    public List<CityStreetResponseDto> findAll(@PathVariable Integer cityId){
        return service.findAll(cityId);
    }

    @GetMapping(GET_BY_ID)
    public CityStreetResponseDto findById(@PathVariable Integer cityId, @PathVariable Integer streetId){
        return service.findByCityIdAndStreetId(cityId, streetId);
    }

    @PostMapping(GET_BY_ID)
    @ResponseStatus(HttpStatus.CREATED)
    public CityStreetResponseDto create(@PathVariable Integer cityId,
                                        @PathVariable Integer streetId,
                                        @RequestBody CityStreetCreateRequestDto dto
    ){
        return service.create(cityId, streetId, dto);
    }

    @PutMapping(GET_BY_ID)
    public CityStreetResponseDto update(@PathVariable Integer cityId,
                                    @PathVariable Integer streetId,
                                    @RequestBody CityStreetUpdateRequestDto dto
    ){
        return service.update(cityId, streetId, dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Integer cityId, @PathVariable Integer streetId){
        return service.delete(cityId, streetId);
    }

}
