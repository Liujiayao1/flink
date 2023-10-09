package com.atguigu.wc;
import com.atguigu.wc.bean.WaterSensor;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MapDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<WaterSensor> waterSensorDS =
                env.fromElements(
                        new WaterSensor("s1", 1l, 1),
                        new WaterSensor("s2", 2l, 2),
                        new WaterSensor("s3", 3l, 3));
        SingleOutputStreamOperator<String> map = waterSensorDS.map(new MapFunction<WaterSensor, String>() {
            @Override
            public String map(WaterSensor value) throws Exception {
                return value.getId();
            }
        });
        map.print();
        env.execute();

    }
}
