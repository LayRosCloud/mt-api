package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.stockmaterial.StockMaterialCreateRequestDto;
import com.trans.api.dto.stockmaterial.StockMaterialResponseDto;
import com.trans.api.dto.stockmaterial.StockMaterialUpdateRequestDto;
import com.trans.api.service.StockMaterialService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "stocks/{stockId}/materials")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class StockMaterialController {
    StockMaterialService service;

    public static final String USE_MATERIAL_ID = "{materialId}";

    @GetMapping
    public List<StockMaterialResponseDto> findAll(@PathVariable Short stockId){
        return service.findAll(stockId);
    }

    @GetMapping(USE_MATERIAL_ID)
    public StockMaterialResponseDto findById(@PathVariable Short stockId, @PathVariable Integer materialId){
        return service.findByStockIdAndMaterialId(stockId, materialId);
    }

    @PostMapping(USE_MATERIAL_ID)
    @ResponseStatus(HttpStatus.CREATED)
    public StockMaterialResponseDto create(@PathVariable Short stockId,
                                           @PathVariable Integer materialId,
                                           @RequestBody StockMaterialCreateRequestDto dto
    ){
        return service.create(stockId, materialId, dto);
    }

    @PutMapping(USE_MATERIAL_ID)
    public StockMaterialResponseDto update(@PathVariable Short stockId,
                                           @PathVariable Integer materialId,
                                           @RequestBody StockMaterialUpdateRequestDto dto
    ){
        return service.update(stockId, materialId, dto);
    }

    @DeleteMapping(USE_MATERIAL_ID)
    public AckDto delete(@PathVariable Short stockId, @PathVariable Integer materialId){
        return service.delete(stockId, materialId);
    }
}
