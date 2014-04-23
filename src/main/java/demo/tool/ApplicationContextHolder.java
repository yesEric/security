package demo.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 应用上下文持有者类
 * 
 * @Description 系统启动时候自动加载
 * @author Watson Xu
 * @date 2013-3-5 下午5:10:43
 * 
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext appCtx;
    
    /**
     * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。
     * @param applicationContext ApplicationContext 对象.
     * @throws BeansException
     */
    @Override
    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
        appCtx = applicationContext;
    }
    
    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     * @param beanName bean的名字
     * @return 返回一个bean对象
     */
    public static Object getBean(String beanName) throws Exception{
    	if(appCtx == null) {
    		throw new Exception("ApplicationContext do not success inject");
    	} 
        return appCtx.getBean(beanName);
    }
    
    
    public static ApplicationContext getApplicationContext() {
    	return appCtx;
    }
}