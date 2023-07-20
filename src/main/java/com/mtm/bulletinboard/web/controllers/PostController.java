package com.mtm.bulletinboard.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.mtm.bulletinboard.bl.dto.PostDto;
import com.mtm.bulletinboard.bl.services.PostService;
import com.mtm.bulletinboard.common.enums.Status;
import com.mtm.bulletinboard.persistance.entity.Post;
import com.mtm.bulletinboard.web.form.PostForm;
import com.mtm.bulletinboard.web.form.mapper.PostFormMapper;

import jakarta.validation.Valid;

@Controller
@RequestMapping("posts")
public class PostController {

	private final PostService postService;
	private final PostFormMapper postFormMapper;

	public PostController(PostService postService,
			PostFormMapper postFormMapper) {
		this.postService = postService;
		this.postFormMapper = postFormMapper;
	}

	/**
	 * show post list page
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String index(Model model) {
		List<PostDto> posts = postService.getAllPosts();
		model.addAttribute("posts", posts);
		return "posts";
	}

	/**
	 * show create page
	 * 
	 * @param confirm
	 * @param model
	 * @param postForm
	 * @return
	 */
	@GetMapping("create")
	public String create(Model model, PostForm postForm) {
		model.addAttribute("postForm", postForm);
		model.addAttribute("confirm", false);
		return "postCreate";
	}
	
	@GetMapping("create/confirm")
	public RedirectView createConfirmRedirect() {
		return new RedirectView("/bulletinboard/posts/create");
	}

	/**
	 * validate and show confirm page
	 * 
	 * @param postForm
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("create/confirm")
	public String createConfirm(
			@Valid @ModelAttribute("postForm") PostForm postForm,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "postCreate";
		}
		model.addAttribute("postForm", postForm);
		model.addAttribute("confirm", true);
		return "postCreate";
	}

	/**
	 * validate and save
	 * 
	 * @param postForm
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("store")
	public RedirectView store(@Valid @ModelAttribute("postForm") PostForm postForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
		    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postForm", result);
	        redirectAttributes.addFlashAttribute("postForm", postForm);
	        return new RedirectView("/bulletinboard/posts/create");
		}
		
		postForm.setStatus(Status.ACTIVE);
		postService.storePost(postFormMapper.apply(postForm));
		return new RedirectView("/bulletinboard/posts?created=true");
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{postId}")
	public String show(@PathVariable("postId") int id) {
		// Show the specified user
		return "";
	}

	/**
	 * show edit page
	 * 
	 * @param postId
	 * @param model
	 * @param postForm
	 * @return
	 */
	@GetMapping("edit/{postId}")
	public String edit(@PathVariable("postId") Integer postId, Model model,
			PostForm postForm) {
		PostDto post = postService.getPost(postId);
		postForm.setDescription(post.description());
		postForm.setStatus(post.status());
		postForm.setTitle(post.title());
		model.addAttribute("postForm", postForm);
		model.addAttribute("postId", postId);
		model.addAttribute("confrim", false);
		return "postEdit";
	}

	@GetMapping("edit/{postId}/confirm")
	public RedirectView editConfirmRedirect(@PathVariable("postId") Integer postId) {
		return new RedirectView("/bulletinboard/posts/edit/" + postId);
	}
	
	@PostMapping("edit/{postId}/confirm")
	public String editConfirm(@Valid @ModelAttribute("postForm") PostForm postForm,
			BindingResult result,
			@PathVariable("postId") Integer postId,
			Model model) {
		if (result.hasErrors()) {
			return "postEdit";
		}
		
		model.addAttribute("postForm", postForm);
		model.addAttribute("postId", postId);
		model.addAttribute("confirm", true);
		return "postEdit";
	}

	/**
	 * validate and show confirm edit page
	 * 
	 * @param postId
	 * @param postForm
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("update/{postId}")
	public RedirectView update(@PathVariable("postId") Integer postId,
	        @Valid @ModelAttribute("postForm") PostForm postForm,
	        BindingResult result, RedirectAttributes redirectAttributes) {
	    if (result.hasErrors()) {
	        return new RedirectView("/bulletinboard/posts/edit/" + postId);
	    }
		if(postForm.getStatus() == null) {
			postForm.setStatus(Status.INACTIVE);
		}
	    System.out.println(postForm.getStatus());
	    postService.updatePost(postId, postFormMapper.apply(postForm));
	    return new RedirectView("/bulletinboard/posts?updated=true");
	}

	@GetMapping("destroy/{postId}")
	public RedirectView destroy(@PathVariable("postId") Integer postId) {
		postService.destroyPost(postId);
		return new RedirectView("/bulletinboard/posts?deleted=true");
	}
}
