import { http } from '@/utils/http/axios';

export interface BasicResponseModel<T = any> {
  code: number;
  message: string;
  data: T;
  ext: string;
  success: boolean;
}

/**
 * @description: 用户登录
 */
export function login(params) {
  return http.request<BasicResponseModel>(
    {
      url: '/developer/monitors/user-manager/login',
      method: 'get',
      params,
    },
    {
      isTransformResponse: false,
    }
  );
}
