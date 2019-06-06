package cn.fdongl.numberwangbackend;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

@Configuration
public class FileSystemConfig {

    public static final String HDFS_PATH="hdfs://192.168.157.129:9000";

//    @Bean
//    FileSystem fileSystem() {
//        try {
//            System.setProperty("hadoop.home.dir", "E:\\winutils-master\\hadoop-3.0.0");
//            System.setProperty("HADOOP_USER_NAME", "root");
//            org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
//            configuration.set("dfs.support.append", "true");
//            configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
//            configuration.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
//            return FileSystem.get(URI.create(HDFS_PATH), configuration);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

}
