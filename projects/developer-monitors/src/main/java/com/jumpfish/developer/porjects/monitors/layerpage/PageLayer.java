package com.jumpfish.developer.porjects.monitors.layerpage;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class PageLayer {
    Long pageSize;
    Long pageNo;
}
