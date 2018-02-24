package com.goubaa.harry.nightplus.Models;

import java.util.List;

/**
 * Created by harry on 2018/1/26.
 */

public class Post {

  /**
   * _id : 5a795333331dc9872b16c821
   * createdAt : 2018-02-06T07:03:15.102Z
   * modifiedAt : 2018-02-08T09:55:06.599Z
   * defaultComponents : [{"content":{"banner":["http://onq4xhob0.bkt.clouddn
   * .com/55a6ff8ec6684c00924883556b7b1fe7"]},"type":"picture","name":"component-banner"},
   * {"content":{"title":""},"type":"word","name":"component-title"},{"content":{"address":""},
   * "type":"word","name":"component-address"},{"content":{"price":""},"type":"word",
   * "name":"component-price"},{"content":{"date":""},"type":"word","name":"component-date"},
   * {"content":{"tel":""},"type":"word","name":"component-tel"},{"content":{"link":"http://baidu
   * .com"},"type":"word","name":"component-link"}]
   * customizedComponents : [[{"content":{"paragraph":"123\n123"},"name":"component-paragraph",
   * "type":"word"},{"content":{"picture":["http://onq4xhob0.bkt.clouddn
   * .com/27f2d763608f40988b7f66178421d0ca"]},"name":"component-picture","type":"picture"},
   * {"content":{"voffer":{"_id":"59ae724e34f57960dafcbef8",
   * "createdAt":"2017-09-05T09:45:50.405Z","modifiedAt":"2017-12-06T08:58:11.492Z",
   * "venueId":"59281d16b5e3cf15cd659aae","name":"哈哈哈","inventory":10,
   * "description":"12瓶百威啤酒+3份小吃+软软+精美果盘一份  通过夜点预定额外赠送两瓶啤酒，希望您玩的愉快","seatType":"小房",
   * "terms":"哈哈哈哈哈","oriPrice":299,"groupPurchase":110,"__v":0,"isDeleted":false,
   * "isDisplay":true,"commodities":[{"name":"百威瓶装啤酒","description":"百威瓶装啤酒","amount":5,
   * "image":"http://onq4xhob0.bkt.clouddn.com/bf9fed95866347e69b7a5532dedca5a6.png",
   * "_id":"59cc6ca885ea6594cb4f4d39","commodity":{"name":"百威瓶装","description":"百威瓶装",
   * "sku":"5904b77ca72c748634e9a646"}}],"affiliates":[],"vofferSales":13,"isExpRefund":true,
   * "isRefund":true,"isPreOrderNeeded":true,"imgs":["http://onq4xhob0.bkt.clouddn
   * .com/e358d30bdf1e442297f00146c66e2ac3.png"],"tags":["测试","随时退","过期自动退"]}},
   * "name":"component-product","type":"voffer"}]]
   * title : 测试
   * postType : 2
   * status : Online
   * index : 1
   * vofferIds : []
   * isRecommend : false
   * isDeleted : false
   * cityIds : ["58d1ecade841a18ba5399026"]
   * location : []
   * shareCount : 0
   * commentCount : 2
   * favoriteCount : 0
   * likeCount : 1
   * informers : [{"_id":"374dc730-058a-11e8-8daf-91d76e950780","userType":"User",
   * "headImgUrl":"http://wx.qlogo
   * .cn/mmopen/vi_32
   * /34ckbHMicFzFI6a7ic5jQcg2BTvPEZdGpS1cteicg94jquk4asMiaXibUnVrv46icaHPiaicOJkmF1a5wRWiaDSGVIVRnQQ/132","displayName":"為妳葑訫","venuesId":null,"mobile":"15161902048","openId":"oNaFgwvgI2XC0QsAXdpj86Au0z64","Time":"2018-02-08T09:55:06.597Z"}]
   * searchTags : ["shift"]
   * affiliates : []
   * tags : [{"tag":"shift","_id":"5a7aa499331dc9872b16c847"}]
   * message : {"images":[]}
   * postedBy : {"_id":"e3ed09d0-ea08-11e7-ac14-6f05fd8e5559","userType":"User",
   * "headImgUrl":"http://wx.qlogo
   * .cn/mmopen/vi_32
   * /Q0j4TwGTfTLRtFj7FBtKSHFhYD36NaKV0lxicoJpibz2X8icNYDJTv3oNjEPOgE6pAwLZ9icYg0lDnJnicr55GEp1LA
   * /0","displayName":"你才","venuesId":"","cityId":"58d1ecade841a18ba5399026",
   * "mobile":"18505102468","title":"夜店小王子","describe":"123",
   * "openId":"oNaFgwsI-P5TvF0VGuPGacBxqHVQ","isFollow":false,"fansCount":6}
   * __v : 1
   * likeId :
   * favoriteId :
   */

  private String _id;
  private String createdAt;
  private String modifiedAt;
  private String title;
  private int postType;
  private String status;
  private int index;
  private boolean isRecommend;
  private boolean isDeleted;
  private int shareCount;
  private int commentCount;
  private int favoriteCount;
  private int likeCount;
  private MessageBean message;
  private PostedByBean postedBy;
  private int __v;
  private String likeId;
  private String favoriteId;
  private List<DefaultComponentsBean> defaultComponents;
  private List<List<CustomizedComponentsBean>> customizedComponents;
  private List<?> vofferIds;
  private List<String> cityIds;
  private List<?> location;
  private List<InformersBean> informers;
  private List<String> searchTags;
  private List<?> affiliates;
  private List<TagsBean> tags;

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(String modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getPostType() {
    return postType;
  }

  public void setPostType(int postType) {
    this.postType = postType;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public boolean isIsRecommend() {
    return isRecommend;
  }

  public void setIsRecommend(boolean isRecommend) {
    this.isRecommend = isRecommend;
  }

  public boolean isIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public int getShareCount() {
    return shareCount;
  }

  public void setShareCount(int shareCount) {
    this.shareCount = shareCount;
  }

  public int getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(int commentCount) {
    this.commentCount = commentCount;
  }

  public int getFavoriteCount() {
    return favoriteCount;
  }

  public void setFavoriteCount(int favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  public int getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(int likeCount) {
    this.likeCount = likeCount;
  }

  public MessageBean getMessage() {
    return message;
  }

  public void setMessage(MessageBean message) {
    this.message = message;
  }

  public PostedByBean getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(PostedByBean postedBy) {
    this.postedBy = postedBy;
  }

  public int get__v() {
    return __v;
  }

  public void set__v(int __v) {
    this.__v = __v;
  }

  public String getLikeId() {
    return likeId;
  }

  public void setLikeId(String likeId) {
    this.likeId = likeId;
  }

  public String getFavoriteId() {
    return favoriteId;
  }

  public void setFavoriteId(String favoriteId) {
    this.favoriteId = favoriteId;
  }

  public List<DefaultComponentsBean> getDefaultComponents() {
    return defaultComponents;
  }

  public void setDefaultComponents(List<DefaultComponentsBean> defaultComponents) {
    this.defaultComponents = defaultComponents;
  }

  public List<List<CustomizedComponentsBean>> getCustomizedComponents() {
    return customizedComponents;
  }

  public void setCustomizedComponents(List<List<CustomizedComponentsBean>> customizedComponents) {
    this.customizedComponents = customizedComponents;
  }

  public List<?> getVofferIds() {
    return vofferIds;
  }

  public void setVofferIds(List<?> vofferIds) {
    this.vofferIds = vofferIds;
  }

  public List<String> getCityIds() {
    return cityIds;
  }

  public void setCityIds(List<String> cityIds) {
    this.cityIds = cityIds;
  }

  public List<?> getLocation() {
    return location;
  }

  public void setLocation(List<?> location) {
    this.location = location;
  }

  public List<InformersBean> getInformers() {
    return informers;
  }

  public void setInformers(List<InformersBean> informers) {
    this.informers = informers;
  }

  public List<String> getSearchTags() {
    return searchTags;
  }

  public void setSearchTags(List<String> searchTags) {
    this.searchTags = searchTags;
  }

  public List<?> getAffiliates() {
    return affiliates;
  }

  public void setAffiliates(List<?> affiliates) {
    this.affiliates = affiliates;
  }

  public List<TagsBean> getTags() {
    return tags;
  }

  public void setTags(List<TagsBean> tags) {
    this.tags = tags;
  }

  public static class MessageBean {
    private List<?> images;

    public List<?> getImages() {
      return images;
    }

    public void setImages(List<?> images) {
      this.images = images;
    }
  }

  public static class PostedByBean {
    /**
     * _id : e3ed09d0-ea08-11e7-ac14-6f05fd8e5559
     * userType : User
     * headImgUrl : http://wx.qlogo
     * .cn/mmopen/vi_32
     * /Q0j4TwGTfTLRtFj7FBtKSHFhYD36NaKV0lxicoJpibz2X8icNYDJTv3oNjEPOgE6pAwLZ9icYg0lDnJnicr55GEp1LA/0
     * displayName : 你才
     * venuesId :
     * cityId : 58d1ecade841a18ba5399026
     * mobile : 18505102468
     * title : 夜店小王子
     * describe : 123
     * openId : oNaFgwsI-P5TvF0VGuPGacBxqHVQ
     * isFollow : false
     * fansCount : 6
     */

    private String _id;
    private String userType;
    private String headImgUrl;
    private String displayName;
    private String venuesId;
    private String cityId;
    private String mobile;
    private String title;
    private String describe;
    private String openId;
    private boolean isFollow;
    private int fansCount;

    public String get_id() {
      return _id;
    }

    public void set_id(String _id) {
      this._id = _id;
    }

    public String getUserType() {
      return userType;
    }

    public void setUserType(String userType) {
      this.userType = userType;
    }

    public String getHeadImgUrl() {
      return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
      this.headImgUrl = headImgUrl;
    }

    public String getDisplayName() {
      return displayName;
    }

    public void setDisplayName(String displayName) {
      this.displayName = displayName;
    }

    public String getVenuesId() {
      return venuesId;
    }

    public void setVenuesId(String venuesId) {
      this.venuesId = venuesId;
    }

    public String getCityId() {
      return cityId;
    }

    public void setCityId(String cityId) {
      this.cityId = cityId;
    }

    public String getMobile() {
      return mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescribe() {
      return describe;
    }

    public void setDescribe(String describe) {
      this.describe = describe;
    }

    public String getOpenId() {
      return openId;
    }

    public void setOpenId(String openId) {
      this.openId = openId;
    }

    public boolean isIsFollow() {
      return isFollow;
    }

    public void setIsFollow(boolean isFollow) {
      this.isFollow = isFollow;
    }

    public int getFansCount() {
      return fansCount;
    }

    public void setFansCount(int fansCount) {
      this.fansCount = fansCount;
    }
  }

  public static class DefaultComponentsBean {
    /**
     * content : {"banner":["http://onq4xhob0.bkt.clouddn.com/55a6ff8ec6684c00924883556b7b1fe7"]}
     * type : picture
     * name : component-banner
     */

    private ContentBean content;
    private String type;
    private String name;

    public ContentBean getContent() {
      return content;
    }

    public void setContent(ContentBean content) {
      this.content = content;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public static class ContentBean {
      private List<String> banner;

      public List<String> getBanner() {
        return banner;
      }

      public void setBanner(List<String> banner) {
        this.banner = banner;
      }
    }
  }

  public static class CustomizedComponentsBean {
    /**
     * content : {"paragraph":"123\n123"}
     * name : component-paragraph
     * type : word
     */

    private ContentBeanX content;
    private String name;
    private String type;

    public ContentBeanX getContent() {
      return content;
    }

    public void setContent(ContentBeanX content) {
      this.content = content;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public static class ContentBeanX {
      /**
       * paragraph : 123
       * 123
       */

      private String paragraph;

      public String getParagraph() {
        return paragraph;
      }

      public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
      }
    }
  }

  public static class InformersBean {
    /**
     * _id : 374dc730-058a-11e8-8daf-91d76e950780
     * userType : User
     * headImgUrl : http://wx.qlogo
     * .cn/mmopen/vi_32
     * /34ckbHMicFzFI6a7ic5jQcg2BTvPEZdGpS1cteicg94jquk4asMiaXibUnVrv46icaHPiaicOJkmF1a5wRWiaDSGVIVRnQQ/132
     * displayName : 為妳葑訫
     * venuesId : null
     * mobile : 15161902048
     * openId : oNaFgwvgI2XC0QsAXdpj86Au0z64
     * Time : 2018-02-08T09:55:06.597Z
     */

    private String _id;
    private String userType;
    private String headImgUrl;
    private String displayName;
    private Object venuesId;
    private String mobile;
    private String openId;
    private String Time;

    public String get_id() {
      return _id;
    }

    public void set_id(String _id) {
      this._id = _id;
    }

    public String getUserType() {
      return userType;
    }

    public void setUserType(String userType) {
      this.userType = userType;
    }

    public String getHeadImgUrl() {
      return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
      this.headImgUrl = headImgUrl;
    }

    public String getDisplayName() {
      return displayName;
    }

    public void setDisplayName(String displayName) {
      this.displayName = displayName;
    }

    public Object getVenuesId() {
      return venuesId;
    }

    public void setVenuesId(Object venuesId) {
      this.venuesId = venuesId;
    }

    public String getMobile() {
      return mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getOpenId() {
      return openId;
    }

    public void setOpenId(String openId) {
      this.openId = openId;
    }

    public String getTime() {
      return Time;
    }

    public void setTime(String Time) {
      this.Time = Time;
    }
  }

  public static class TagsBean {
    /**
     * tag : shift
     * _id : 5a7aa499331dc9872b16c847
     */

    private String tag;
    private String _id;

    public String getTag() {
      return tag;
    }

    public void setTag(String tag) {
      this.tag = tag;
    }

    public String get_id() {
      return _id;
    }

    public void set_id(String _id) {
      this._id = _id;
    }
  }
}
