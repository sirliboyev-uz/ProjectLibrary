package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Entayti.*;
import com.example.projectlibrary.Repozitory.*;
import com.example.projectlibrary.Service.KitobService;
import com.example.projectlibrary.Service.XizmatKorsatishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    KitobRepazitory kitobRepazitory;

    @Autowired
    KitobService kitobService;

    @Autowired
    YangilikRepazitory yangilikRepazitory;

    @Autowired
    IjaraBookRepazitory ijaraBookRepazitory;

    @Autowired
    QrcodebookRepazitory qrcodebookRepazitory;

    @Autowired
    XizmatKorsatishRepository xizmatKorsatishRepository;


    @Autowired
    AboutUsRepasitoey aboutUsRepasitoey;

    @GetMapping("/home")
    public String Home(Model map){

        List<Kitob> jahon = kitobRepazitory.findByKatagoriya("Jahon adabiyoti");
        List<Kitob> badiy = kitobRepazitory.findByKatagoriya("Badiy adabiyoti");
        List<Kitob> ilmiy = kitobRepazitory.findByKatagoriya("Ilmiy adabitoti");
        List<Yangilik> yangilik = yangilikRepazitory.findAll();
        List<IjaraBook> ijaraBooks = ijaraBookRepazitory.findAll();
        List<XizmatKorsatish> xizmatKorsatishList = xizmatKorsatishRepository.findAll();

        map.addAttribute("JahonAdabiyot", jahon);
        map.addAttribute("BadiyAdabiyot", badiy);
        map.addAttribute("IlmiyAdabiyot", ilmiy);
        map.addAttribute("Yangilik",yangilik);
        map.addAttribute("IjaraBooks",ijaraBooks);
        map.addAttribute("Xizmatlar",xizmatKorsatishList);
        return "Home/index";
    }

    @GetMapping("/addbook")
    public String Addbooks(Model map){
        List<Kitob> full = kitobRepazitory.findAll();
        map.addAttribute("IlmiyAdabiyot", full);
        return "kitoblar";
    }

    @GetMapping("/buyurtma")
    public String buyurtmaBerish(Model map){
        List<Kitob> jahon = kitobRepazitory.findByKatagoriya("Jahon adabiyoti");
        map.addAttribute("JahonAdabiyot", jahon);
        return "buyurtma";
    }


//    Kitobni yuklab olish

    @GetMapping("/file/dowlond/{id}")
    public void DBYuklash(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Kitob> optionalFayl=kitobRepazitory.findById(id);
        if (optionalFayl.isPresent()){
            response.setContentType(optionalFayl.get().getKitobSours().getFileContentType());
            response.setHeader("Content-Disposition","attachment; faylname=\""+optionalFayl.get().getKitobSours().getArginalName()+"\"");
            FileCopyUtils.copy(optionalFayl.get().getKitobSours().getFileByte(),response.getOutputStream());
        }
    }



//   Rasmni olish

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Kitob> byId = kitobRepazitory.findById(id);
        if (byId.isPresent()){
            Kitob kitob = byId.get();
            response.setContentType(kitob.getKitobSours().getContentType());
            response.getOutputStream().write(kitob.getKitobSours().getImageByte());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/image/ijara/{id}")
    @ResponseBody
    void ijarashowimg(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<IjaraBook> byId = ijaraBookRepazitory.findById(id);
        if (byId.isPresent()){
            IjaraBook kitob = byId.get();
            response.setContentType(kitob.getIjaraBookSourse().getContentType());
            response.getOutputStream().write(kitob.getIjaraBookSourse().getImageByte());
            response.getOutputStream().close();
        }
    }


    @GetMapping("/dowlond/{id}")
    public String down(@PathVariable Integer id , Model map){
        List<Kitob> full = kitobRepazitory.findAll();
        for (Kitob i:full) {
            if (i.getId().equals(id)) map.addAttribute("IlmiyAdabiyot", i);
        }
        return "dowlond";
    }

    @GetMapping("/ijara/kitob")
    public String zakazBerish(@PathVariable Integer id,Model map){
        List<IjaraBook> bookList = ijaraBookRepazitory.findAll();
        for (IjaraBook i : bookList){
            if (i.getId().equals(id)){
                map.addAttribute("IjaraKitob",i);
            }
        }
        return "zakaz";
    }



//    @GetMapping("/login/{id}")
//    public String Addlogin(@RequestParam Integer id, Model map){
//        Optional<IjaraBook> ijaraBooks = ijaraBookRepazitory.findById(id);
//        map.addAttribute("IjaraBooks1")
//        return "login";
//    }

    @GetMapping("/register")
    public String AddRegister(Model map){
        return "register";
    }

    @GetMapping("/contact-us-panel")
    public String panel(Model map){
        List<XizmatKorsatish> list = xizmatKorsatishRepository.findAll();
        map.addAttribute("XizmatKorsatish",list);
        map.addAttribute("Button",list.size());
        return "contact-us-panel";
    }

    @GetMapping("/about-uz-panel")
    public String Aboutuz(Model map){
        List<AboutUs> list = aboutUsRepasitoey.findAll();
        map.addAttribute("AboutUs" , list);
        return "about-uz-panel";
    }

    @GetMapping("/addNew")
    public String AddNew(Model map){
        List<Yangilik> yangiliks = yangilikRepazitory.findAll();
        map.addAttribute("FullYangilik",yangiliks);
        return "addNew";
    }

    @GetMapping("/image/new/{id}")
    @ResponseBody
    void showImage1(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Yangilik> byId = yangilikRepazitory.findById(id);
        if (byId.isPresent()){
            Yangilik yangilik = byId.get();
            response.setContentType(yangilik.getYangilikSourse().getContentType());
            response.getOutputStream().write(yangilik.getYangilikSourse().getImageByte());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/image/about/{id}")
    @ResponseBody
    void showImage1123(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<AboutUs> byId = aboutUsRepasitoey.findById(id);
        if (byId.isPresent()){
            AboutUs yangilik = byId.get();
            response.setContentType(yangilik.getAboutUsSourse().getContentType());
            response.getOutputStream().write(yangilik.getAboutUsSourse().getRasm());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/ijara/img/{id}")
    @ResponseBody
    void ijaraImgShow(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<IjaraBook> byId = ijaraBookRepazitory.findById(id);
        if (byId.isPresent()){
            IjaraBook yangilik = byId.get();
            response.setContentType(yangilik.getIjaraBookSourse().getContentType());
            response.getOutputStream().write(yangilik.getIjaraBookSourse().getImageByte());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/qr/img/{id}")
    @ResponseBody
    void QRImgShow(@PathVariable("id") Integer id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Qrcodebook> byId = qrcodebookRepazitory.findById(id);
        if (byId.isPresent()){
            Qrcodebook yangilik = byId.get();
            response.setContentType(yangilik.getQrcodebookSourse().getContentType());
            response.getOutputStream().write(yangilik.getQrcodebookSourse().getImageByte());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/admin")
    public String admin_panel(Model map){
        return "admin-panel";
    }

    @GetMapping("/ijara/books")
    public String ijarabooks(Model map){
        List<IjaraBook> list = ijaraBookRepazitory.findAll();
        map.addAttribute("IjaraBook",list);
        return "ijarabooks";
    }

    @GetMapping("/qr/books")
    public String qrabooks(Model map){
        List<Qrcodebook> list = qrcodebookRepazitory.findAll();
        map.addAttribute("QrcodeBook",list);
        return "qrcodebook";
    }

    @GetMapping("/tadbirlar")
    public String tadbirlar(Model map){
        return "tadbirlar";
    }

    @GetMapping("/about")
    public String About(){
        return "About/about";
    }




    @GetMapping("/books/{value}")
    public String Books(@PathVariable String value , Model map){

        if (value.equals("0")){
            List<Kitob> full = kitobRepazitory.findAll();
            map.addAttribute("fullBooks", full);
        }
       if (value.equals("jahon")){
           List<Kitob> full = kitobRepazitory.findByKatagoriya("Jahon adabiyoti");
           map.addAttribute("fullBooks", full);
       }
        if (value.equals("badiy")){
            List<Kitob> full = kitobRepazitory.findByKatagoriya("Badiy adabiyoti");
            map.addAttribute("fullBooks", full);
        }
        if (value.equals("ilmiy")){
            List<Kitob> full = kitobRepazitory.findByKatagoriya("Ilmiy adabitoti");
            map.addAttribute("fullBooks", full);
        }
        if (value.equals("full")){
            List<Kitob> full = kitobRepazitory.findAll();
            map.addAttribute("fullBooks", full);
        }
        return "Books/books";
    }

    @GetMapping("/books")
    public String Books1(Model map){

            List<Kitob> full = kitobRepazitory.findAll();
            map.addAttribute("fullBooks", full);

        return "Books/books";
    }

    @GetMapping("/qrbooks")
    public String qebooks(Model map){
        List<Kitob> full = kitobRepazitory.findAll();
        map.addAttribute("fullBooks", full);
        return "Books/qrbook";
    }

    @GetMapping("/ijara")
    public String ijara(Model map){
        List<Kitob> full = kitobRepazitory.findAll();
        map.addAttribute("fullBooks", full);
        return "Books/ijara";
    }

    @GetMapping("/cantact")
    public String Contact(Model map){
        return "contact";
    }



    @GetMapping("/news")
    public String News(Model map){
        List<Yangilik> yangiliks = yangilikRepazitory.findAll();
        map.addAttribute("FullNews",yangiliks);
        return "News/news";
    }

    @GetMapping("/users")
    public String Users(Model map){

        return "users";
    }

//    ============================     //

    @GetMapping("/fullnew")
    public String fullNews(){
        return "News/fullNews";
    }

    @GetMapping("/admin-add-news")
    public String admin_add_news(){
        return "Admin/yangiliklar";
    }

    @GetMapping("/admin-login")
    public String admin_login(){
        return "login";
    }

}
