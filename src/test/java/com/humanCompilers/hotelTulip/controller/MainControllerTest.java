package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.*;
import com.humanCompilers.hotelTulip.service.TarifaMeetingRoomService;
import com.humanCompilers.hotelTulip.service.TarifaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Clase que realiza el testeo del controller principal de la aplicación
 * @author Human Compilers
 */
@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarifaService tarifaService;

    @MockBean
    private TarifaMeetingRoomService tarifaMeetingRoomService;

    static Tarifa tarifa1;
    static TarifaMeetingRoom tarifa2;

    /**
     * Método que sirve para inicializar todas las variables a utilizar en la clase
     */
    @BeforeAll
    public static void init() {
        tarifa1 = new Tarifa();
        tarifa1.setSeason(Season.MID);
        tarifa1.setRoom_type(HotelRoomType.SINGLE);
        tarifa1.setPrice(50.0);

        tarifa2 = new TarifaMeetingRoom();
        tarifa2.setSeason(Season.MID);
        tarifa2.setRoom_type(MeetingRoomType.SMALL);
        tarifa2.setPrice(150.0);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    void tarifas() throws Exception {
        String tarifa1Str = tarifa1.getSeason().getSeason().toLowerCase() + "_" + tarifa1.getRoom_type().getRoomType().toLowerCase();
        String tarifa2Str = tarifa2.getSeason().getSeason().toLowerCase() + "_" + tarifa2.getRoom_type().getMeetingRoomType().toLowerCase();

        List<Tarifa> tarifas = new ArrayList<>();
        tarifas.add(tarifa1);

        List<TarifaMeetingRoom> tarifasMeetingRoom = new ArrayList<>();
        tarifasMeetingRoom.add(tarifa2);

        when(tarifaService.getAllTarifas()).thenReturn(tarifas);
        when(tarifaMeetingRoomService.getAllTarifasMeetingRoom()).thenReturn(tarifasMeetingRoom);

        this.mockMvc.perform(get("/tarifas"))
                .andExpect(status().isOk())
                .andExpect(view().name("prices"))
                .andExpect(model().attributeExists(tarifa1Str))
                .andExpect(model().attributeExists(tarifa2Str));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    void contact() throws Exception{
        this.mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void cookies() throws Exception {
        this.mockMvc.perform(get("/privacyPolicy"))
                .andExpect(status().isOk())
                .andExpect(view().name("cookies"));
    }
}