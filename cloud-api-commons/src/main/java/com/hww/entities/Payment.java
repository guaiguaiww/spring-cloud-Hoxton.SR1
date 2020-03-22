package com.hww.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/14-23:55
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    private long id;
    private String serial;



}
