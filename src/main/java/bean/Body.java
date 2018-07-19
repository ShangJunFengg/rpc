package bean;

import java.util.Arrays;

public class Body implements java.io.Serializable{

    //全限定类型名
    private String className;

    //调用的方法名
    private String  methodName;

    //方法参数类型
    private Class<?>[] paramerTypes;

    //参数列表
    private Object[] paramValues;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamerTypes() {
        return paramerTypes;
    }

    public void setParamerTypes(Class<?>[] paramerTypes) {
        this.paramerTypes = paramerTypes;
    }

    public Object[] getParamValues() {
        return paramValues;
    }

    public void setParamValues(Object[] paramValues) {
        this.paramValues = paramValues;
    }

    @Override
    public String toString() {
        return "Body{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramerTypes=" + Arrays.toString(paramerTypes) +
                ", paramValues=" + Arrays.toString(paramValues) +
                '}';
    }
}
