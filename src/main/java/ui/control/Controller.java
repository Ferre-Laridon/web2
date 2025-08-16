package ui.control;

import domain.DomainException;
import domain.db.KoffieDB;
import domain.model.Koffie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private KoffieDB koffies = new KoffieDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String command = "home";
        if (request.getParameter("command") != null) {
            command = request.getParameter("command");
        }

        String destination;
        switch (command) {
            case "home":
                destination = home(request, response);
                break;
            case "addForm":
                destination = addForm(request, response);
                break;
            case "add":
                destination = add(request, response);
                break;
            case "overview":
                destination = overview(request, response);
                break;
            case "zoekFormulier":
                destination = zoekFormulier(request, response);
                break;
            case "result":
                destination = result(request, response);
                break;
            case "confirmationPage":
                destination = confirmationPage(request, response);
                break;
            case "delete":
                destination = delete(request, response);
                break;
            case "edit":
                destination = edit(request, response);
                break;
            case "editConfirmation":
                destination = editConfirmation(request, response);
                break;
            default:
                destination = home(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    //setters
    private void setNaam(Koffie koffie, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");

        try {
            koffie.setNaam(naam);
            request.setAttribute("naamPreviousValue", naam);
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }
    }

    private void setGramKoffie(Koffie koffie, HttpServletRequest request, ArrayList<String> errors) {
        String gramKoffie = request.getParameter("gramKoffie");

        try {
            koffie.setGramKoffie(Double.parseDouble(gramKoffie));
            request.setAttribute("gramKoffiePreviousValue", gramKoffie);
        } catch (NumberFormatException e) {
            errors.add("Vul een waarde in voor het aantal gram koffie.");
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }
    }

    private void setMlWater(Koffie koffie, HttpServletRequest request, ArrayList<String> errors) {
        String milliliterWater = request.getParameter("milliliterWater");

        try {
            koffie.setMlWater(Integer.parseInt(milliliterWater));
            request.setAttribute("milliliterWaterPreviousValue", milliliterWater);
        } catch (NumberFormatException e) {
            errors.add("Vul een waarde in voor het aantal milliliter water.");
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }
    }

    private void setMlMelk(Koffie koffie, HttpServletRequest request, ArrayList<String> errors) {
        String milliliterMelk = request.getParameter("milliliterMelk");
        if (milliliterMelk.equals("")) milliliterMelk = "0";

        try {
            koffie.setMlMelk(Integer.parseInt(milliliterMelk));
            request.setAttribute("milliliterMelkPreviousValue", milliliterMelk);
        } catch (NumberFormatException e) {
            errors.add("Vul een waarde in voor het aantal milliliter melk.");
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }
    }

    // Pagina's
    private String home(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("koffies", koffies.getAll());
        if (!koffies.getAll().isEmpty())
            request.setAttribute("meesteCaff", koffies.getHoogsteVerhoudingCaff());
        return "index.jsp";
    }

    private String addForm(HttpServletRequest request, HttpServletResponse response) {
        return "add.jsp";
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {

        ArrayList<String> errors = new ArrayList<>();

        Koffie koffie = new Koffie();
        setNaam(koffie, request, errors);
        setGramKoffie(koffie, request, errors);
        setMlWater(koffie, request, errors);
        setMlMelk(koffie, request, errors);

        if (errors.isEmpty()) {
            try {
                koffies.add(koffie);

                Cookie cookie = aantalToegevoegd(request, response);
                request.setAttribute("aantalToegevoegd", cookie.getValue());

                return overviewWithCookie(request, response);
            } catch (DomainException e) {
                errors.add(e.getMessage());
            }
        }

        request.setAttribute("errors", errors);
        return "add.jsp";
    }

    private String overviewWithCookie(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("koffies", koffies.getAll());
        if (koffies.getAll().isEmpty()) {
            request.setAttribute("foutmelding", "De lijst is momenteel leeg.");
        }
        return "overview.jsp";
    }

    private String overview(HttpServletRequest request, HttpServletResponse response) {
        // attribuut aantalToegevoegd ophalen
        Cookie cookie = getCookieWithKey(request, "aantalToegevoegd");
        if (cookie == null) request.setAttribute("aantalToegevoegd", 0);
        else request.setAttribute("aantalToegevoegd", cookie.getValue());
        return overviewWithCookie(request, response);
    }

    private String zoekFormulier(HttpServletRequest request, HttpServletResponse response) {
        return "zoekFormulier.jsp";
    }

    private String result(HttpServletRequest request, HttpServletResponse response) {
        String vindNaam = request.getParameter("vindNaam");

        if (koffies.getKoffie(vindNaam) != null) {
            request.setAttribute("gevondenKoffie", koffies.getKoffie(vindNaam));
        } else {
            request.setAttribute("vindNaam", vindNaam);
        }
        return "result.jsp";
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String idKoffie = request.getParameter("id");
        int id = Integer.parseInt(idKoffie);
        Koffie koffie = koffies.getKoffieById(id);
        try {
            // Sessions
            if (koffie != null) {
                HttpSession session = request.getSession();
                session.setAttribute("laatstVerwijderdeKoffie", koffie);

                koffies.delete(koffie);
            } else {
                request.getSession().invalidate();
            }
            return overview(request, response);
        } catch (DomainException e) {
            request.setAttribute("foutmelding", "Kon koffie niet verwijderen.");
            return overview(request, response);
        }
    }

    private String confirmationPage(HttpServletRequest request, HttpServletResponse response) {
        String idKoffie = request.getParameter("id");
        int id = Integer.parseInt(idKoffie);
        request.setAttribute("verwijderKoffie", koffies.getKoffieById(id));
        return "confirmationPage.jsp";
    }

    private String edit(HttpServletRequest request, HttpServletResponse response) {
        String idKoffie = request.getParameter("id");
        int id = Integer.parseInt(idKoffie);
        request.setAttribute("koffie", koffies.getKoffieById(id));
        return "editConfirmation.jsp";
    }

    private String editConfirmation(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        String veranderId = request.getParameter("oudeId");
        int id = Integer.parseInt(veranderId);
        Koffie veranderen = koffies.getKoffieById(id);

        Koffie vervanger = new Koffie();
        setNaam(vervanger, request, errors);
        setGramKoffie(vervanger, request, errors);
        setMlWater(vervanger, request, errors);
        setMlMelk(vervanger, request, errors);

        if (errors.isEmpty()) {
            try {
                koffies.vervang(veranderen, vervanger);
                return overview(request, response);
            } catch (DomainException e) {
                errors.add(e.getMessage());
            }
        }

        request.setAttribute("errors", errors);
        request.setAttribute("koffie", veranderen);
        return "editConfirmation.jsp";

    }

    // Cookies
    private Cookie aantalToegevoegd(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookieWithKey(request, "aantalToegevoegd");
        Cookie c;

        if (cookie == null) {
            c = new Cookie("aantalToegevoegd", "1");
        } else {
            int counter = Integer.parseInt(cookie.getValue());
            counter++;
            String aantalToegevoegd = Integer.toString(counter);
            c = new Cookie("aantalToegevoegd", aantalToegevoegd);
        }
        response.addCookie(c);
        return c;
    }

    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }

}
