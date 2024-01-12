package com.jumpfish.developer.porjects.monitors.layertime;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class TimeLayer {
    private Long request_time;
    private Long request_adjust_time;

}
