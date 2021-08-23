package com.vm.test.component;


import javax.validation.constraints.*;

/**
 * @author huaihai.guan
 * @since 2021/8/23 14:37
 */
public class ParamBean {

    @NotNull
    private String name;

    @Min(value = 18)
    private Integer age;

    @Max(value = 1)
    @Min(value = 0)
    private Integer sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
