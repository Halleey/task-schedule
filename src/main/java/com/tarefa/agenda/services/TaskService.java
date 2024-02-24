package com.tarefa.agenda.services;
import com.tarefa.agenda.entities.Task;
import com.tarefa.agenda.entities.User;
import com.tarefa.agenda.exception.IdException;
import com.tarefa.agenda.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    public Task saveTask(Task task) {
        return repository.save(task);
    }
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getTasksByUser(User user) {
        return repository.findByUser(user);
    }
    public void deleteTaskById(Long taskId) {
        repository.deleteById(taskId);
    }

    @Transactional
    public void alterTaskById(Long taskId, String novoNome, String novaDescricao) {
        // Verifica se a tarefa com o ID fornecido existe no banco de dados
        Task task = repository.findById(taskId).orElseThrow(() -> new IdException(taskId));
        
        if (task != null) {
            // Se a tarefa existir, atualiza os campos desejados
            if (novoNome != null) {
                task.setName(novoNome);
            }
            if (novaDescricao != null) {
               task.setDescricao(novaDescricao);
            }
            // Salva a tarefa atualizada no banco de dados
            repository.save(task);
        }
    }
}
