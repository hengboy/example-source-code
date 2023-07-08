package org.minbox.learning.flink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * 读取Socket传输的文本内容
 * 控制台通过netcat命令开启本地9999端口的监听：nc -l 9999
 *
 * @author 恒宇少年
 */
public class ReadSocketTextExample {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> streamSource = env.socketTextStream("localhost", 9999);

        DataStream<String> afterSplitDataStream = streamSource.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String s, Collector<String> collector) throws Exception {
                String[] split = s.split(",");
                for (String s1 : split) {
                    // 过滤仅"a"开头的字符串
                    if (s1.startsWith("a")) {
                        collector.collect(s1);
                    }
                }
            }
        });

        afterSplitDataStream.print();

        env.execute();
    }
}
