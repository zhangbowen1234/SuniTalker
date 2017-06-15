package com.silver.chat.network.responsebean;

import java.util.List;

/**
 * Created by Joe on 2017/5/15.
 */
public class SearchGroupBean {
    @Override
    public String toString() {
        return "SearchGroupBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", groups=" + groups +
                '}';
    }

    /**
     * pageNum : 1当前页码
     * pageSize : 3每页显示数量
     * total : 1总页码
     * groups : [{"groupId":25,"groupName":"测试群1","targetImId":234,"avatar":"localhost:7103/imx/leaf/user/group/update/avatar","describe":"本群创建于2017/02/17: 新建群组","authentication":1},{"groupId":63,"groupName":"测试群","targetImId":14,"avatar":"http://swift.sspaas.net:8080/v1/AUTH_bbc95ed02b384596a0d3ec33c934cdb7/imicon/99461wuchun20173810023827.png","describe":"本群创建于2017/03/01: 三国杀曹丕say","authentication":1}]
     */

    private int pageNum;
    private int pageSize;
    private int total;
    private List<GroupsBean> groups;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<GroupsBean> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsBean> groups) {
        this.groups = groups;
    }

    public static class GroupsBean {
        @Override
        public String toString() {
            return "GroupsBean{" +
                    "groupId=" + groupId +
                    ", groupName='" + groupName + '\'' +
                    ", targetImId=" + targetImId +
                    ", avatar='" + avatar + '\'' +
                    ", describe='" + describe + '\'' +
                    ", authentication=" + authentication +
                    '}';
        }

        /**
         * groupId : 25
         * groupName : 测试群1
         * targetImId : 234
         * avatar : localhost:7103/imx/leaf/user/group/update/avatar
         * describe : 本群创建于2017/02/17: 新建群组
         * authentication : 1
         */

        private int groupId;
        private String groupName;
        private int targetImId;
        private String avatar;
        private String describe;
        private int authentication;

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getTargetImId() {
            return targetImId;
        }

        public void setTargetImId(int targetImId) {
            this.targetImId = targetImId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public int getAuthentication() {
            return authentication;
        }

        public void setAuthentication(int authentication) {
            this.authentication = authentication;
        }
    }

}
