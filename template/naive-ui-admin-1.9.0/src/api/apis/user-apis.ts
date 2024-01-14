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
      url: '/api/developer/monitors/user-manager/login',
      method: 'get',
      params,
    },
    {
      isTransformResponse: false,
    }
  );
}

/**
 * @description: 用户信息
 */
export function getUser(access_token) {
  return http.request<BasicResponseModel>(
    {
      url: '/api/developer/monitors/access-user/getUser',
      method: 'get',
      params: { 'access_token': access_token }
    },
    {
      isTransformResponse: false,
    }
  );
}