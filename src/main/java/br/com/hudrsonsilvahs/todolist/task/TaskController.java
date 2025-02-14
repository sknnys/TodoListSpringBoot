package br.com.hudrsonsilvahs.todolist.task;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();

       if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("data de inicio e de termino deve ser maior q atual");

       }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt()) ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("data de inicio tem que ser menor que a de termino");

        }

        var task = taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        return this.taskRepository.findByIdUser((UUID) idUser);
    }
}
