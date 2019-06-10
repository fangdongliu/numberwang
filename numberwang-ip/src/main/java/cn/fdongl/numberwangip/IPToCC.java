package cn.fdongl.numberwangip;
import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;



public class IPToCC extends GenericUDF {

    public static void main(String[] args) throws IOException, GeoIp2Exception {
        DatabaseReader reader = new DatabaseReader.Builder(new File("C:\\Users\\LiuFangdong\\Downloads\\GeoLite2-City_20190604\\GeoLite2-City.mmdb")).withCache(new CHMCache()).build();
        InetAddress ip = InetAddress.getByName("119.186.196.47");
        CityResponse cityResponse = reader.city(ip);
        System.out.println(cityResponse);
    }

    DatabaseReader reader=null;

    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        try {
            reader = new DatabaseReader.Builder(new File("GeoLite2-City.mmdb")).withCache(new CHMCache()).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> structFieldNames = new ArrayList<String>();
        ArrayList<ObjectInspector>fieldTypes = new ArrayList<ObjectInspector>();

        structFieldNames.add("country");
        structFieldNames.add("province");
        structFieldNames.add("city");
        fieldTypes.add(PrimitiveObjectInspectorFactory.writableStringObjectInspector);
        fieldTypes.add(PrimitiveObjectInspectorFactory.writableStringObjectInspector);
        fieldTypes.add(PrimitiveObjectInspectorFactory.writableStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(structFieldNames,fieldTypes);
    }

    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        Object [] res = new Object[3];
        try {
            InetAddress ip = InetAddress.getByName(arguments[0].get().toString());
            CityResponse cityResponse = reader.city(ip);
            res[0] = cityResponse.getCountry().getNames().get("zh-CN");
            if(res[0]==null){
                res[0]="";
            }
            if(cityResponse.getSubdivisions().size()>0){
                res[2] = cityResponse.getSubdivisions().get(0).getNames().get("zh-CN");
                if(res[2]==null){
                    res[2]="";
                }
            }
            res[1] = cityResponse.getCity().getNames().get("zh-CN");
            if(res[1]==null){
                res[1]="";
            }
        } catch (Exception e) {
        }
        return res;
    }

    @Override
    public String getDisplayString(String[] children) {
        return "Usage: IPToCC(String ip)";
    }
}
