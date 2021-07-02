package com.example.zoobox.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KHospital {

    private String BIZPLC_NM;
    private String LICENSG_DE;
    private String BSN_STATE_NM;
    private boolean CLSBIZ_DE;
    private String REFINE_LOTNO_ADDR;
    private String REFINE_ROADNM_ADDR;

}
