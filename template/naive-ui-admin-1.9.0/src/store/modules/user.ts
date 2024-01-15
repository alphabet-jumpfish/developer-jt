import { defineStore } from 'pinia';
import { store } from '@/store';
import { ACCESS_TOKEN, CURRENT_USER, IS_SCREENLOCKED } from '@/store/mutation-types';
import { ResultEnum } from '@/enums/httpEnum';

import { getUserInfo as getUserInfoApi, login } from '@/api/system/user';

import { login as commit, getUser as userInfo } from '@/api/apis/user-apis';

import { storage } from '@/utils/Storage';

export type UserInfoType = {
  // TODO: add your own data
  username: string;
  email: string;
  userId: number;
  password: string;
};

export interface IUserState {
  token: string;
  username: string;
  welcome: string;
  avatar: string;
  permissions: any[];
  info: UserInfoType;
}

export type StorageUserInfo = {
  name: string;
  email: string;
};


export const useUserStore = defineStore({
  id: 'app-user',
  state: (): IUserState => ({
    token: storage.get(ACCESS_TOKEN, ''),
    username: '',
    welcome: '',
    avatar: '',
    permissions: [],
    info: storage.get(CURRENT_USER, {}),
  }),
  getters: {
    getToken(): string {
      return this.token;
    },
    getAvatar(): string {
      return this.avatar;
    },
    getNickname(): string {
      return this.username;
    },
    getPermissions(): [any][] {
      return this.permissions;
    },
    getUserInfo(): UserInfoType {
      return this.info;
    },
  },
  actions: {
    setToken(token: string) {
      this.token = token;
    },
    setAvatar(avatar: string) {
      this.avatar = avatar;
    },
    setPermissions(permissions) {
      this.permissions = permissions;
    },
    setUserInfo(info: UserInfoType) {
      this.info = info;
    },

    // 【developer-jt】【登陆】
    async commit(params: any) {
      const response = await commit(params);
      const { code, data } = response;
      if (code === ResultEnum.RESULT_SUCCESS) {

        const current_user_info: UserInfoType = {
          username: data.loginUsername,
          email: data.loginUsername,
          userId: data.userId,
          password: data.loginUsername
        }

        const ex = 7 * 24 * 60 * 60;
        storage.set(ACCESS_TOKEN, data.access_token, ex);
        storage.set(CURRENT_USER, current_user_info, ex);
        storage.set(IS_SCREENLOCKED, false);
        this.setToken(data.access_token);
        this.setUserInfo(current_user_info);
      }
      return response;
    },

    // 登录
    async login(params: any) {
      const response = await login(params);
      const { result, code } = response;
      if (code === ResultEnum.SUCCESS) {
        const ex = 7 * 24 * 60 * 60;
        storage.set(ACCESS_TOKEN, result.token, ex);
        storage.set(CURRENT_USER, result, ex);
        storage.set(IS_SCREENLOCKED, false);
        this.setToken(result.token);
        this.setUserInfo(result);
      }
      return response;
    },

    async gInfo() {
      const access_token = storage.get(ACCESS_TOKEN);
      const result = await userInfo(access_token);
      // {
      //   "code": 0,
      //   "message": "请求成功!",
      //   "data": {
      //       "authorities": null,
      //       "username": "13675831750",
      //       "userId": 1,
      //       "labelCode": null,
      //       "fullName": null
      //   },
      //   "ext": null,
      //   "success": true
      // }
      const permissions = result.data.authorities;
      const info = result.data;
      const avatar = "http://examlpe";
      if (permissions && permissions.length) {
        this.setPermissions(permissions);
      }
      const current_user_info: UserInfoType = {
        username: info.username,
        email: info.username,
        userId: info.userId,
        password: info.username
      }
      this.setUserInfo(current_user_info);
      this.setAvatar(avatar);
      return result;
    },

    // 获取用户信息
    async getInfo() {
      const result = await getUserInfoApi();
      if (result.permissions && result.permissions.length) {
        const permissionsList = result.permissions;
        this.setPermissions(permissionsList);
        this.setUserInfo(result);
      } else {
        throw new Error('getInfo: permissionsList must be a non-null array !');
      }
      this.setAvatar(result.avatar);
      return result;
    },

    // 登出
    async logout() {
      this.setPermissions([]);
      //this.setUserInfo({ name: '', email: '' });
      storage.remove(ACCESS_TOKEN);
      storage.remove(CURRENT_USER);
    },
  },
});

// Need to be used outside the setup
export function useUser() {
  return useUserStore(store);
}
