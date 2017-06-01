package com.silver.chat.database.helper;


import com.silver.chat.AppContext;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.network.responsebean.GroupBean;

import java.sql.SQLException;

/**
 * 数据库辅助类
 */
public class DBHelper extends BaseDBHelper {

    //版本号
    private static final int DB_VERSION = 2;

    //数据库名称
    private static final String DB_NAME = "ssimdemo.db";

    //数据表清单
    private static final Class<?>[] tables = {
            ContactListBean.class,GroupBean.class
    };

    private static DBHelper helper = null;

    /**
     * 获取单例
     * @return
     */
    public static DBHelper get() {
        if (null == helper) {
            synchronized (DBHelper.class) {
                if (null == helper) {
                    helper = new DBHelper();
                }
            }
        }
        return helper;
    }

    private DBHelper() {
        super(AppContext.getInstance(), DB_NAME, null, DB_VERSION, tables);
    }

    @Override
    protected BaseDBHelper initHelper() {
        return get();
    }

    @Override
    protected boolean upgrade(int oldVersion, int newVersion) throws SQLException {
        if (oldVersion < 2) {
            getDao(ContactListBean.class).executeRaw("ALTER TABLE'contact_list' ADD COLUMN MD5 TEXT DEFAULT 'default';");
        }
        return true;
    }

}
