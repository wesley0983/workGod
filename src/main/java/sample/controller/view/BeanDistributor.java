package sample.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import sample.controller.CompanyController;
import sample.controller.ReportController;
import sample.jfxsupport.AbstractFxmlView;
import sample.jfxsupport.PrototypeController;

import java.util.HashMap;
import java.util.Map;

@Component
public class BeanDistributor {

    @Autowired
    private ApplicationContext context;

    public Map<String,AbstractFxmlView> getViewBean(String beanName){
        Map<String,AbstractFxmlView> map = new HashMap<>();
        switch (beanName){
            case "CompanyView":
                map.put(beanName,context.getBean(CompanyView.class));
                break;
            case "ReportView":
                map.put(beanName,context.getBean(ReportView.class));
                break;
        }
        return map;
    }

    public Map<String,PrototypeController> getControllerBean(String beanName){
        Map<String, PrototypeController> map = new HashMap<>();
        switch (beanName){
            case "CompanyController":
                map.put(beanName,context.getBean(CompanyController.class));
                break;
            case "ReportController":
                map.put(beanName,context.getBean(ReportController.class));
                break;
        }
        return map;
    }

//    private static ConfigurableApplicationContext applicationContext;
//
//    public static void showView(final Class<? extends AbstractFxmlView> newView) {
//        final AbstractFxmlView view = applicationContext.getBean(newView);
//    }
//
//    public AbstractFxmlView gettt(String viewName){
//        Class<? extends AbstractFxmlView> S = getBean(viewName);
//        AbstractFxmlView bean = applicationContext.getBean(S);
//
////        applicationContext.getBeanNamesForType()
//
//        return bean;
//    }
}
