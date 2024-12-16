```
@ConfigurationProperties(prefix = "auth")
@Data
public class AuthorizationProperties {
    private List<String> whiteList;
}
@EnableConfigurationProperties(value = AuthorizationProperties.class)

Error creating bean with name 'storageConfig': Injection of resource dependencies failed
Error creating bean with name 'upload-com.shuting.rbac.config.properties.UploadProperties': Invocation of init method failed

upload:
  static-dir: /caoshuting/IdeaProjects/RBAC/
  
  Error creating bean with name 'storageConfig': Unsatisfied dependency expressed through field 'uploadProperties': Error creating bean with name 'upload-com.shuting.rbac.config.properties.UploadProperties': Invocation of init method failed
  
  @ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {
    private String staticDir;

    @PostConstruct
    public void init() {
        if(StrUtil.isBlank(staticDir)){
            throw new RuntimeException("Upload.staticDir not configured");
        }
        if(!staticDir.endsWith(CommonConstants.SLASH)){
            throw new RuntimeException("Upload.staticDir not ended with '/'");
        }
        File file = new File(staticDir);
        if(!file.isAbsolute()){
            throw new RuntimeException("Upload.staticDir is not absolute");
        }
        if(!file.exists()){
            try{
                FileUtils.forceMkdir(file);
            }catch(IOException e){
                throw new RuntimeException("upload.staticDir create failed");
            }
        }
    }
}


@Configuration
@EnableConfigurationProperties(value = UploadProperties.class)
public class StorageConfig implements WebMvcConfigurer {
    @Autowired
    private UploadProperties uploadProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] locations = {"classpath:/META-INF/resources","classpath:/resources",
                "classpath:/static/", "classpath:/public/",
                "file:" + uploadProperties.getStaticDir()
        };
        registry.addResourceHandler("/static/**")
                .addResourceLocations(locations);
    }
}

```

