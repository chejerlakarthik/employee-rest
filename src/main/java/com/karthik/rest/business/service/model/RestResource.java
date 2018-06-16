package com.karthik.rest.business.service.model;

import java.util.ArrayList;
import java.util.List;

import com.karthik.rest.hateoas.Link;

public class RestResource {

	private List<Link> links = new ArrayList<Link>();

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String href, String rel) {
		Link link = new Link();
		link.setHref(href);
		link.setRel(rel);
		getLinks().add(link);
	}
	
	public void resetLinks() {
		this.links = new ArrayList<>();
	}

}
