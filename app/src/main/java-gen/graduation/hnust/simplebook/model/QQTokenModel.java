package graduation.hnust.simplebook.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "QQTOKEN_MODEL".
 */
public class QQTokenModel {

    private Long id;
    private Long userId;
    private String openId;
    private String accessToken;
    private String ret;
    private String payToken;
    private String pf;
    private String queryAuthorityCost;
    private String expiresIn;
    private String pfKey;
    private String msg;
    private String loginCost;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public QQTokenModel() {
    }

    public QQTokenModel(Long id) {
        this.id = id;
    }

    public QQTokenModel(Long id, Long userId, String openId, String accessToken, String ret, String payToken, String pf, String queryAuthorityCost, String expiresIn, String pfKey, String msg, String loginCost, java.util.Date createdAt, java.util.Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.openId = openId;
        this.accessToken = accessToken;
        this.ret = ret;
        this.payToken = payToken;
        this.pf = pf;
        this.queryAuthorityCost = queryAuthorityCost;
        this.expiresIn = expiresIn;
        this.pfKey = pfKey;
        this.msg = msg;
        this.loginCost = loginCost;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getPayToken() {
        return payToken;
    }

    public void setPayToken(String payToken) {
        this.payToken = payToken;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getQueryAuthorityCost() {
        return queryAuthorityCost;
    }

    public void setQueryAuthorityCost(String queryAuthorityCost) {
        this.queryAuthorityCost = queryAuthorityCost;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getPfKey() {
        return pfKey;
    }

    public void setPfKey(String pfKey) {
        this.pfKey = pfKey;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLoginCost() {
        return loginCost;
    }

    public void setLoginCost(String loginCost) {
        this.loginCost = loginCost;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}