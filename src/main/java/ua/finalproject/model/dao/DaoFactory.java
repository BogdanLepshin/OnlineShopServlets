package ua.finalproject.model.dao;

import ua.finalproject.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract ProductDao createProductDao();
    public abstract CategoryDao createCategoryDao();
    public abstract BrandDao createBrandDao();
    public abstract CartDao createCartDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
