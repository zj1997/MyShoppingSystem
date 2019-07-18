package cn.zj.pojo;

/**
 * @author zhaojie
 * @date 2019\7\17 0017 - 13:53
 */
public class User {

    //记录id
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //用户生日
    private String birthday;
    //会员卡账号
    private String vipid;
    //用户积分
    private Integer usermoney;
    //会员等级 0普通会员 1金卡会员
    private Integer viplevel;

    public String getViplevelDetail() {

        if(viplevel == 0){
            return "普通会员";
        }else if(viplevel == 1){
            return "金卡会员";
        }
        return "";
    }

    public Integer getViplevel() {
        return viplevel;
    }

    public void setViplevel(Integer viplevel) {
        this.viplevel = viplevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getVipid() {
        return vipid;
    }

    public void setVipid(String vipid) {
        this.vipid = vipid;
    }

    public Integer getUsermoney() {
        return usermoney;
    }

    public void setUsermoney(Integer usermoney) {
        this.usermoney = usermoney;
    }
}
