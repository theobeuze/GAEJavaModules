Google App Engine with 2 services in Java


Two different module: achat and vente using memcache to exchange information.

Don't forget tu update the : appengine-modules-ear/src/main/application/META-INF/appengine-application.xml

    git clone https://github.com/theobeuze/GAEJavaModules.git
    cd appengine-modules-sample-java
    mvn install
    cd appengine-modules-main
    #to test it locally:
    mvn appengine:devserver
    #or to deploy it:
    mvn appengine:update

=============================
