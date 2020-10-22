package com.humanCompilers.hotelTulip.controller;


import com.humanCompilers.hotelTulip.model.Area;
import com.humanCompilers.hotelTulip.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AreaController {

    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }



    @GetMapping("/areas")
    public List<Area> areas(Model model) {
        /*List<Area> areas = new ArrayList<>();

        Area area1 = new Area(UUID.randomUUID(), 2, 50, Tipo.HABITACION);
        Area area2 = new Area(UUID.randomUUID(), 3, 60, Tipo.SALA);

        areas.add(area1);
        areas.add(area2);*/

        List<Area> dbAreas = areaService.getAllAreas();

        //System.out.println(dbAreas.get(0));

        /*model.addAttribute("name", "Laura");
        model.addAttribute("areas", dbAreas);*/
        return dbAreas;
    }

    @PostMapping("/areas")
    public void addArea(@Valid @NonNull @RequestBody Area area) {
        //System.out.println(area);
        areaService.addArea(area);
    }
}
