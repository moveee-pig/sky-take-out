package com.sky.controller.user;

@RestController
@RequestMapping("/user/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 微信登入
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("微信登入")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信用户登录:{}",userLoginDTO.getCode());
        //我微信用户登入
        User user=userService.wxLogin(userLoginDTO);
        //生产JWT令牌
        Map<String,Object> claims =new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getAdminTtl(), claims);
        UserLoginVO userLoginVO=new UserLoginVO(user.getId(),user.getOpenid(),token);
 
        return Result.success(userLoginVO);
    }