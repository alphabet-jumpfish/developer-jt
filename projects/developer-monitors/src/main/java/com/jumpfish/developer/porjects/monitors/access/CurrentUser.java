package com.jumpfish.developer.porjects.monitors.access;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class CurrentUser {
   private String accessToken;
   private String userName;
   private Long userId;
   private Long tenantId;
}
