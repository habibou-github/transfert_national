package com.TransfertNational.demo.Services.ServicesImpl;
import com.TransfertNational.demo.Entities.Admin;
import com.TransfertNational.demo.Entities.Agent;
import com.TransfertNational.demo.Repositorys.AgenceRepository;
import com.TransfertNational.demo.Repositorys.AgentRepository;
import com.TransfertNational.demo.Services.AgentService;
import com.TransfertNational.demo.Shared.Utils;
import com.TransfertNational.demo.Shared.dto.AgentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentRepository agentRepository;
    @Autowired
    AgenceRepository agenceRepository;
    @Autowired
    Utils util;
    @Override
    public AgentDto createAgent(AgentDto agent) {
        Agent agentEntity = new Agent();
        BeanUtils.copyProperties(agent,agentEntity);
        agentEntity.setEncryptedPassword(util.EncryptePassword(agent.getPassword()));
        agentEntity.setAgence(agenceRepository.findById(agent.getAgenceId()).orElse(null));

        agentRepository.save(agentEntity);
        return agent;
    }

    @Override
    public AgentDto getAgentById(long id) {
        Agent agentEntity = agentRepository.findById(id).orElseThrow(() -> new RuntimeException("Ce agent n'esxiste pas"));
        AgentDto agentDto = new AgentDto();
        BeanUtils.copyProperties(agentEntity,agentDto);
        agentDto.setAgenceId(agentEntity.getAgence().getId());
        agentDto.setPassword(agentEntity.getEncryptedPassword());

        return agentDto;
    }

    @Override
    public AgentDto updateAgent(long id, AgentDto agent) {
        Agent agentEntity = agentRepository.findById(id).orElseThrow(() -> new RuntimeException("Ce agent n'esxiste pas"));
        if(agent.getUsername() != null)
            agentEntity.setUsername(agent.getUsername());
        if(agent.getPassword()!= null)
            agentEntity.setEncryptedPassword(util.EncryptePassword(agent.getPassword()));
        if(agent.getAgenceId() == null)
            agentEntity.setAgence(agenceRepository.findById(agent.getAgenceId()).orElseThrow(() -> new RuntimeException("Ce id de Agence n'existe pas")));
        Agent agentUpdated = agentRepository.save(agentEntity);
        return getAgentById(agent.getId());

    }

    @Override
    public void deleteAgent(long id) {
        Agent agentEntity = agentRepository.findById(id).orElseThrow(() -> new RuntimeException("Ce agent n'esxiste pas"));
        agentRepository.delete(agentEntity);
    }

    @Override
    public List<AgentDto> getAgents(String search) {
        List<AgentDto> agentDtoList = new ArrayList<>();
        List<Agent> agentList;
        if(search.isEmpty()) {
            agentList = agentRepository.findAllAgents();
        }
        else {
            agentList = agentRepository.findAllAgentsByCriteria(search);
        }
        for(Agent agentEntity: agentList){
            AgentDto agentDto = new AgentDto();
            BeanUtils.copyProperties(agentEntity,agentDto);
            agentDtoList.add(agentDto);

        }

        return agentDtoList;
    }

    @Override
    public Boolean logIn(String username, String password){

        String encryptedPassword = password;

        List<Agent> allAgents = agentRepository.findAllAgents();
        for (Agent a :allAgents){
            if (a != null){

                if (a.getUsername().equals(username)){
                    if (a.getEncryptedPassword().equals(encryptedPassword)){
                        return true;
                    }
                    System.out.println("password incorrect");
                    return false;
                }
                System.out.println("username incorrect");
                return false;
            }
        }
        System.out.println("NO AGENT");
        return false;

    }



}
