import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java.util.Util;

public class Main {
			static List<Article> articles;
			
			static {
				articles = new ArrayList();
			}
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		makeTestDate();
		Scanner sc = new Scanner(System.in);

		int lastArticleId = 3;

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
					System.out.println("번호  |   제목    |     조회수");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("%d  |   %s  |    %d\n", article.id, article.title, article.hit);
					}
				}
			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getNowDateStr();
				Article article = new Article(id, regDate, title, body);
				articles.add(article);
				System.out.printf("%d번 게시글이 생성되었습니다.\n", id);
				lastArticleId++;
			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", id);
					continue;
				}
				foundArticle.increaseHit();
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성시간 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.hit);

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물이 없습니다.\n", id);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

		}

	}

	private static void makeTestDate() {
		System.out.println("테스트데이터 3개를 생성완료했습니다.");
		articles.add(new Article(1, Util.getNowDateStr(), "제목 1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목 2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목 3", "내용3", 33));
	}
}

class Article {
	int id;
	String title;
	String body;
	String regDate;
	int hit;

	public Article(int id, String regDate, String title, String body) {
		this(id, regDate,title,body,0);
	}

	public Article(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}
}