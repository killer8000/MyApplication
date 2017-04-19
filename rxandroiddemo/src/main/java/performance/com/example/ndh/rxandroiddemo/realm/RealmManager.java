package performance.com.example.ndh.rxandroiddemo.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ndh on 17/4/7.
 */

public class RealmManager {

    private static Realm sRealm;

    private RealmManager() {
    }

    public static Realm getInstance(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("news.realm") //文件名
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()// 冲突后删表
//                .encryptionKey(new byte[]{'2', '3', '2', '3', '2', '3', '2', '3', '2', '3', '2', '3',})
                .build();
        sRealm = Realm.getInstance(config);
        return sRealm;
    }


}
