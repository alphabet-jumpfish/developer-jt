<template>
  <BasicForm @register="register" @submit="handleSubmit" @reset="handleReset"/>
</template>

<script lang="ts">

import { defineComponent, ref } from 'vue';
import { BasicForm, FormSchema, useForm } from '@/components/Form/index';

const schemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '姓名',
    labelMessage: '这是一个提示',
    defaultValue: '小马哥',
    componentProps: {
      placeholder: '请输入姓名',
      onInput: (e: any) => {
        console.log(e);
      },
    },
    rules: [{ required: true, message: '请输入姓名', trigger: ['blur'] }],
  }
];

export default defineComponent({
  components: { BasicForm },
  setup() {
    const formRef: any = ref(null);

    const [register, { setFieldsValue }] = useForm({
      gridProps: { cols: 1 },
      collapsedRows: 3,
      labelWidth: 120,
      layout: 'horizontal',
      schemas,
    });

    function handleSubmit(values: Recordable) {
      console.log(values);
    }

    function handleReset(values: Recordable) {
      console.log(values);
    }

    return {
      schemas,
      register,
      formRef,
      handleSubmit,
      handleReset,
    };
  },
});
</script>
