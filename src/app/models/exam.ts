export type ExamStatus = 'EM_ANDAMENTO' | 'CONCLUIDO' | 'CANCELADO';
export type ExamType = 'SANGUE' | 'RAIO_X' | 'ULTRASSONOGRAFIA' | 'RESSONANCIA' | 'URINA' | 'TOMOGRAFIA';

export class Exam {

    id?: number;
    cpf: string;
    data: string;
    name: string;
    status?: ExamStatus;
    type?: ExamType;

    constructor(cpf: string, data: string, name: string, status: ExamStatus, type: ExamType, id?: number) {
        this.id = id;
        this.cpf = cpf;
        this.data = data;
        this.name = name;
        this.status = status;
        this.type = type;
    }

}