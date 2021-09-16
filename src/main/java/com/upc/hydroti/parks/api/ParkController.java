package com.upc.hydroti.parks.api;

import com.upc.hydroti.parks.application.dto.ParkDetailResponse;
import com.upc.hydroti.parks.application.dto.ParkResponse;
import com.upc.hydroti.parks.application.dto.UpdateParkRequest;
import com.upc.hydroti.parks.infra.entity.ParkEntity;
import org.modelmapper.ModelMapper;
import com.upc.hydroti.parks.application.dto.AddParkRequest;
import com.upc.hydroti.parks.application.services.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parks")
public class ParkController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ParkService parkService;

    @GetMapping()
    public List<ParkResponse> getAllParks() {
        return parkService.getAllParks().stream()
                .map(this::convertToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{parkId}")
    public ParkDetailResponse getParkById(@PathVariable(name = "parkId") Long id) {
        return mapper.map(parkService.getParkById(id), ParkDetailResponse.class);
    }

    @PostMapping()
    public ParkResponse addPark(@RequestBody AddParkRequest request) {
        ParkEntity park = convertToEntity(request);
       return convertToResponse(parkService.addPark(park));
    }

    @PutMapping("/{parkId}")
    public ParkDetailResponse updatePark(@PathVariable(name = "parkId") Long id, @Valid @RequestBody UpdateParkRequest request) {
        ParkEntity park = convertUpdateRequestToEntity(request);
        return mapper.map(parkService.updatePark(id, park), ParkDetailResponse.class);
    }

    @DeleteMapping("/{parkId}")
    public ResponseEntity<?> deletePark(@PathVariable(name = "parkId")Long id) {
        return parkService.deletePark(id);
    }


    private ParkEntity convertToEntity(AddParkRequest request) { return mapper.map(request, ParkEntity.class);}

    private ParkEntity convertUpdateRequestToEntity(UpdateParkRequest request) { return mapper.map(request, ParkEntity.class);}

    private ParkResponse convertToResponse(ParkEntity entity) { return mapper.map(entity, ParkResponse.class);}
}
