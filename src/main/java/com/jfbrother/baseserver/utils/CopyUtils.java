package com.jfbrother.baseserver.utils;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.core.PageOb;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用于对象拷贝（主要用于JPA对象和传输模型之间的转换）
 *
 * @author chenyl@jfbrother.com
 */
public class CopyUtils {
    /**
     * 获取对象的空字段名称数组
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 单个对象拷贝
     *
     * @param start            原数据
     * @param end              结果数据
     * @param eClazz           结果数据类型
     * @param ignoreProperties 拷贝时，需要忽略的字段
     * @return
     */
    public static <E> E copyBean(Object start, E end, Class<E> eClazz, String... ignoreProperties) {
        try {
            if (end == null) {
                end = eClazz.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        BeanUtils.copyProperties(start, end, ignoreProperties);
        return end;
    }

    /**
     * 单个对象拷贝
     *
     * @param start            原数据
     * @param end              结果数据
     * @param ignoreProperties
     * @return
     */
    public static <E> E copyBean(Object start, E end, String... ignoreProperties) {
        BeanUtils.copyProperties(start, end, ignoreProperties);
        return end;
    }

    /**
     * 单个对象拷贝(过滤null值)
     *
     * @param start            原数据
     * @param end              结果数据
     * @param ignoreProperties
     * @return
     */
    public static <E> E copyBeanFilterNull(Object start, E end, String... ignoreProperties) {
        BeanUtils.copyProperties(start, end, ListUtils.concat(getNullPropertyNames(start), ignoreProperties));
        return end;
    }

    /**
     * 单个对象拷贝
     *
     * @param start            原数据
     * @param eClazz           结果数据类型
     * @param ignoreProperties 拷贝时，需要忽略的字段
     * @return
     */
    public static <E> E copyBean(Object start, Class<E> eClazz, String... ignoreProperties) {
        return copyBean(start, null, eClazz, ignoreProperties);
    }

    /**
     * list内的对象同名属性拷贝
     *
     * @param start  源
     * @param end    结果
     * @param eClazz 结果类型
     * @return
     */
    public static <E> List<E> copyList(List start, List<E> end, Class<E> eClazz) {
        try {
            if (end == null) {
                end = new ArrayList<E>();
            }
            if (start != null && start.size() > 0) {
                for (Object s : start) {
                    end.add(copyBean(s, eClazz));
                }
            }
            return end;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * list内的对象同名属性拷贝
     *
     * @param start  源
     * @param eClazz 结果类型
     * @return
     */
    public static <E> List<E> copyList(List start, Class<E> eClazz) {
        return copyList(start, null, eClazz);
    }

    /**
     * list 自定义规则 对象拷贝
     *
     * @param start      源
     * @param end        结果
     * @param eClazz     结果类型
     * @param startClazz 源类型
     * @param mapper     映射
     * @param <E>
     * @param <F>
     * @return
     */
    public static <E, F> List<E> copyList(List<F> start, List<E> end, Class<F> startClazz, Class<E> eClazz, Function<F, E> mapper) {
        try {
            if (end == null) {
                end = new ArrayList<E>();
            }
            if (start != null && start.size() > 0) {
                for (F s : start) {
                    end.add(mapper.apply(s));
                }
            }
            return end;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * list 自定义规则 对象拷贝
     *
     * @param start      源
     * @param eClazz     结果类型
     * @param startClazz 源类型
     * @param mapper     映射
     * @param <E>
     * @param <F>
     * @return
     */
    public static <E, F> List<E> copyList(List<F> start, Class<F> startClazz, Class<E> eClazz, Function<F, E> mapper) {
        return copyList(start, null, startClazz, eClazz, mapper);
    }

    /**
     * 翻页对象拷贝
     *
     * @param start  源翻页对象
     * @param end    结果翻页对象
     * @param eClazz 结果数据类型
     * @return
     */
    public static <E> PageOb<E> copyPageOb(Page start, PageOb<E> end, Class<E> eClazz) {
        end.setPage(start.getNumber());
        end.setSize(start.getSize());
        end.setTotalCount(start.getTotalElements());
        end.setResult(copyList(start.getContent(), eClazz));
        return end;
    }

    public static <E, F> PageOb<E> copyPageOb(Page start, PageOb<E> end, Class<F> startClazz, Class<E> eClazz, Function<F, E> mapper) {
        end.setPage(start.getNumber());
        end.setSize(start.getSize());
        end.setTotalCount(start.getTotalElements());
        end.setResult(copyList(start.getContent(), startClazz, eClazz, mapper));
        return end;
    }

    /**
     * 翻页对象拷贝
     *
     * @param start  源翻页对象
     * @param eClazz 结果数据类型
     * @return
     */
    public static <E> PageOb<E> copyPageOb(Page start, Class<E> eClazz) {
        PageOb<E> pageUsersModel = new PageOb<E>();
        return copyPageOb(start, pageUsersModel, eClazz);
    }

    /**
     * 翻页对象拷贝
     *
     * @param start      源翻页对象
     * @param eClazz     结果数据类型
     * @param startClazz 开始的数据类型
     * @param mapper     映射
     * @return
     */
    public static <E, F> PageOb<E> copyPageOb(Page start, Class<F> startClazz, Class<E> eClazz, Function<F, E> mapper) {
        PageOb<E> pageUsersModel = new PageOb<E>();
        return copyPageOb(start, pageUsersModel, startClazz, eClazz, mapper);
    }

    /**
     * 从QueryResults<Tuple> 对象拷贝得到PageOb翻页对象
     *
     * @param results 源
     * @param end     目标
     * @param eClazz  目标数据类型
     * @param mapper  拷贝规则
     * @param <E>     目标数据对象
     * @return
     */
    public static <E> PageOb<E> copyPageObByQueryResults(QueryResults<Tuple> results, PageOb<E> end, Class<E> eClazz, Function<Tuple, E> mapper) {
        if (end == null) {
            end = new PageOb<>();
        }
        end.setPage((int) (results.getOffset() / results.getLimit()));

        //TODO 临时解决办法，如果数量超过int的最大值，则全部设置为10（需要改！！！）
        if (results.getLimit() > 2147483647) {
            end.setSize(10);
        } else {
            end.setSize((int) results.getLimit());
        }

        end.setTotalCount(results.getTotal());

        end.setResult(results.getResults().stream().map(mapper).collect(Collectors.toList()));
        return end;
    }

    public static <E> PageOb<E> copyPageObByQueryResults(QueryResults<E> results) {
        PageOb<E> end = new PageOb<>();
        if (end == null) {
            end = new PageOb<>();
        }
        end.setPage((int) (results.getOffset() / results.getLimit()));

        //TODO 临时解决办法，如果数量超过int的最大值，则全部设置为10（需要改！！！）
        if (results.getLimit() > 2147483647) {
            end.setSize(10);
        } else {
            end.setSize((int) results.getLimit());
        }

        end.setTotalCount(results.getTotal());

        end.setResult(results.getResults());
        return end;
    }

    /**
     * 从QueryResults<Tuple> 对象拷贝得到PageOb翻页对象
     *
     * @param results 源
     * @param eClazz  目标数据对象
     * @param mapper  拷贝规则
     * @param <E>     目标数据对象
     * @return
     */
    public static <E> PageOb<E> copyPageObByQueryResults(QueryResults<Tuple> results, Class<E> eClazz, Function<Tuple, E> mapper) {
        return copyPageObByQueryResults(results, null, eClazz, mapper);
    }

    /**
     * 数据字典转化
     *
     * @param dictMap
     * @param start
     * @param eClazz
     * @param <E>
     * @return
     */
    public static <E> E copyDictList(Map<String, Map<String, String>> dictMap, Object start, Class<E> eClazz) {
        JSONObject _data = JSONObject.parseObject(JSONObject.toJSONString(start));

        //内部
        JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(start));
        //循环map
        if (dictMap.size() > 0) {
            for (Map.Entry<String, Map<String, String>> entry : dictMap.entrySet()) {
                String key = entry.getKey();
                if (StringUtils.isEmpty(data.get(key))) {
                    continue;
                }
                data.put(key, entry.getValue().get(data.get(key).toString()));
            }
        }
        _data.put("data", data);

        return JSONObject.toJavaObject(_data, eClazz);
    }
}
