import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		List<Article> articles = new ArrayList<Article>();
		
		while (true) {
			
			System.out.printf("명령문 ) ");
			String command = sc.nextLine();
			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;

			}
			if (command.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				return;
			}
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
				} else {
					System.out.println("번호  |   제목");
					for(int i =articles.size()-1; i>=0; i--) {
						Article article = articles.get(i);
						System.out.printf("%d  |   %s\n",article.id,article.title);
					}
				}
			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				articles.add(article);
				System.out.printf("%d번 게시글이 생성되었습니다.\n", id);
				lastArticleId++;
			} else {
				System.out.println("잘못된 명령어입니다.");
				continue;
			}

		}

	}
}

class Article {
	int id;
	String title;
	String body;

	Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}