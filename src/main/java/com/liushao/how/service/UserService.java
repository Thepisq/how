package com.liushao.how.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import com.liushao.how.domain.User;
import com.liushao.how.repository.UserRepository;
import com.liushao.how.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
	
	@Autowired
	private IdWorker idWorker;

	// @Autowired
    // private RedisTemplate redisTemplate;

	@Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private HttpServletRequest request;

    public User login(String mobile, String password) {
        User user = userRepository.findByMobilePhone(mobile);
        if(user!=null && encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User findByMobile(String mobile) {
        return userRepository.findByMobilePhone(mobile);
    }

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return userRepository.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userRepository.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return userRepository.findById(id).get();
	}

	/**
	 * 增加
	 * @param user
	 */
	public void add(User user) {
		user.setId( idWorker.nextId()+"" );
		//密码加密
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRegtime(new Date());//注册日期
		userRepository.save(user);
	}

	/**
	 * 修改
	 * @param user
	 */
	public void update(User user) {
		userRepository.save(user);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
        String token = (String) request.getAttribute("chaims_admin");
        if(token==null || "".equals(token)) {
            throw new RuntimeException("权限不足！");
        }
        userRepository.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

            private static final long serialVersionUID = 4836393138512549228L;

            @Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (!searchMap.get("id").toString().isEmpty()) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 手机号码
                if (!searchMap.get("mobilePhone").toString().isEmpty()) {
                	predicateList.add(cb.like(root.get("mobilePhone").as(String.class), "%"+(String)searchMap.get("mobilePhone")+"%"));
                }
                // 密码
                if (!searchMap.get("password").toString().isEmpty()){
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // 昵称
                if (!searchMap.get("nickname").toString().isEmpty()) {
                	predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
                }
                // 性别
                if (!searchMap.get("sex").toString().isEmpty()) {
                	predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
                }
                // 头像
                if (!searchMap.get("avatar").toString().isEmpty()) {
                	predicateList.add(cb.like(root.get("avatar").as(String.class), "%"+(String)searchMap.get("avatar")+"%"));
                }
                // E-Mail
                if (!searchMap.get("email").toString().isEmpty()) {
                	predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
                }
                // ip
                if (!searchMap.get("ip").toString().isEmpty()) {
                	predicateList.add(cb.like(root.get("ip").as(String.class), "%"+(String)searchMap.get("ip")+"%"));
                }
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};

	}

    /*public void sendSms(String mobile) {
        //生成六位数字随机数
        String checkcode = RandomStringUtils.randomNumeric(6);

        //向缓存中放一份
        redisTemplate.opsForValue().set("checkcode_"+mobile, checkcode, 6, TimeUnit.HOURS);

        //给用户发一份
        Map<String,String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("checkcode", checkcode);
        rabbitTemplate.convertAndSend("sms", map);

        //在控制台显示一份【方便测试】
        System.out.println("验证码为："+checkcode);
    }

    public void updateFanscountAndFollowcount(int x, String userid, String friendid) {
	    userRepository.updateFanscount(x, friendid);
	    userRepository.updateFollowcount(x, userid);
    }*/
}