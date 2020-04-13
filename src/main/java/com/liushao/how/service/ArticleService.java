package com.liushao.how.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.liushao.how.domain.Article;
import com.liushao.how.repository.ArticleRepository;
import com.liushao.how.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
	
	@Autowired
    private IdWorker idWorker;
    
    public Article findById(String id) {
        return articleRepository.findById(id).get();
    }

    public void add(Article article){
        article.setId(idWorker.nextId()+"");
        articleRepository.save(article);
    }

    public void update(String id, Article article){
        article.setId(id);
        articleRepository.save(article);
    }

    public void deleteById(String id){
        articleRepository.deleteById(id);;
    }

    /**
     * 批量删除文章
     * @param ids 文章id数组
     */
    public void batchDeleteIds(List<String> ids){
        List<Article> list =new ArrayList<>();
        for(String id : ids){
            Article article =new Article();
            article.setId(id);
            list.add(article);
        }
        articleRepository.deleteInBatch(list);
    }

    /**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Article> findSearch(Map whereMap, int page, int size) {
		Specification<Article> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return articleRepository.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Article> findSearch(Map whereMap) {
		Specification<Article> specification = createSpecification(whereMap);
		return articleRepository.findAll(specification);
    }
    
    /**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Article> createSpecification(Map searchMap) {

		return new Specification<Article>() {

            private static final long serialVersionUID = 1L;

            @Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (!searchMap.get("id").toString().isEmpty()) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 标题
                if (!searchMap.get("articleTital").toString().isEmpty()){
                	predicateList.add(cb.like(root.get("articleTital").as(String.class), "%"+(String)searchMap.get("articleTital")+"%"));
                }
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};

	}

}