package com.sky.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 @Data
 这是Lombok库提供的一个注解，用于自动生成getter、setter、equals()、hashCode()和toString()方法。
 使用这个注解可以极大地减少类的样板代码，使代码更简洁易读。

 @Builder:
 这也是Lombok库的一个注解，用于生成构建者模式的代码。
 构建者模式是一种创建对象的模式，它允许你通过链式调用设置对象的属性，最后调用build()方法来创建对象。
 这使得对象的创建和配置更加流畅和易读。

 @NoArgsConstructor:
 Lombok注解，用于生成一个无参构造函数。
 在某些情况下，比如使用序列化或某些框架时，可能需要无参构造函数。

 @AllArgsConstructor:
 Lombok注解，用于生成一个全参构造函数。
 这使得你可以通过构造函数一次性设置对象的所有属性。

 @ApiModel(description = "员工登录返回的数据格式"):
 这是Swagger框架的一个注解，用于API文档生成。
 description属性提供了对该模型的描述，这里描述的是“员工登录返回的数据格式”。
 在生成的Swagger文档中，这个描述会出现在对应模型的地方，帮助开发者理解模型的作用和含义。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "员工登录返回的数据格式")
public class EmployeeLoginVO implements Serializable {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("jwt令牌")
    private String token;

}
