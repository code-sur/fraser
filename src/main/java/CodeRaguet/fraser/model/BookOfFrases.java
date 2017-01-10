package CodeRaguet.fraser.model;

public interface BookOfFrases {
    Frase next();

    Frase nextFraseAfter(Bookmark bookmark);
}
